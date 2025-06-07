<?php

use App\Models\User;
use App\Models\WasteType;
use Illuminate\Foundation\Testing\RefreshDatabase;

uses(Tests\TestCase::class, RefreshDatabase::class);

describe('Waste Type API', function() {

    test('user yang terautentikasi dapat melihat jenis sampah.', function () {
        $user = User::factory()->create();
        WasteType::factory()->count(10)->create();

        $this->actingAs($user)
            ->getJson('/api/waste-types')
            ->assertStatus(200)
            ->assertJsonCount(10, 'data');
    });

    test('user yang tidak terautentikasi tidak dapat melihat jenis sampah.', function () {
        $this->getJson('/api/waste-types')
            ->assertStatus(401);
    });

})->group('waste-types', 'feature');
