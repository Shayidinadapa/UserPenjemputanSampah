<?php

use App\Models\Category;
use App\Models\User;
use Illuminate\Foundation\Testing\RefreshDatabase;

uses(Tests\TestCase::class, RefreshDatabase::class);

describe('Category API', function() {

    test('user yang terautentikasi dapat melihat kategori.', function () {
        $user = User::factory()->create();
        Category::factory()->count(5)->create();

        $this->actingAs($user)
            ->getJson('/api/categories')
            ->assertStatus(200)
            ->assertJsonCount(5, 'data');
    });

    test('user yang tidak terautentikasi tidak dapat melihat kategori.', function () {
        $this->getJson('/api/categories')
            ->assertStatus(401);
    });

})->group('categories', 'feature');
