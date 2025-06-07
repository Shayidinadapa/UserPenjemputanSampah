<?php

use App\Models\User;
use Illuminate\Foundation\Testing\RefreshDatabase;

uses(Tests\TestCase::class, RefreshDatabase::class);

describe('Authentication API', function () {

    test('user baru dapat mendaftar dengan sukses.', function () {
        $userData = [
            'name' => 'User',
            'email' => 'user@gmail.com',
            'password' => 'test123',
            'phone' => '081234567890',
        ];

        $this->postJson('/api/register', $userData)
            ->assertStatus(201)
            ->assertJsonStructure(['data' => ['user', 'token']]);

        $this->assertDatabaseHas('users', [
            'email' => 'user@gmail.com',
        ]);
    });

    test('user tidak dapat mendaftar dengan alamat email yang sudah digunakan.', function () {
        User::factory()->create(['email' => 'user@gmail.com']);

        $userData = [
            'name' => 'User Baru',
            'email' => 'user@gmail.com',
            'password' => 'userbaru123',
            'phone' => '089876543210',
        ];

        $this->postJson('/api/register', $userData)
            ->assertStatus(422)
            ->assertJsonValidationErrors(['email']);
    });

    test('user yang sudah terdaftar dapat masuk dengan kredensial yang benar.', function () {
        $password = 'test123';
        $user = User::factory()->create(['password' => $password]);

        $this->postJson('/api/login', ['email' => $user->email, 'password' => $password])
            ->assertStatus(200)
            ->assertJsonPath('data.user.email', $user->email);
    });

    test('user yang terautentikasi dapat keluar dengan sukses.', function () {
        $user = User::factory()->create();

        $this->actingAs($user)
            ->postJson('/api/logout')
            ->assertStatus(200)
            ->assertJson(['message' => 'Logout Success']);
    });

})->group('auth', 'feature');
