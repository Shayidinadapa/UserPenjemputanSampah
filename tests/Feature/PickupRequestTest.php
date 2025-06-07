<?php

use App\Models\Category;
use App\Models\PickupRequest;
use App\Models\User;
use Illuminate\Foundation\Testing\RefreshDatabase;

uses(Tests\TestCase::class, RefreshDatabase::class);

describe('Pickup API', function () {

    beforeEach(function () {
        $this->user = User::factory()->create();
        $this->actingAs($this->user);
    });

    test('user dapat membuat permintaan pengambilan.', function () {
        $category = Category::factory()->create();
        $pickupData = [
            'waste_type_name' => 'Smartphone',
            'category_id' => $category->id,
            'weight' => 5.5,
        ];

        $this->postJson('/api/pickups', $pickupData)
            ->assertStatus(201)
            ->assertJsonPath('data.waste_details.name', 'Smartphone');

        $this->assertDatabaseHas('pickup_requests', [
            'user_id' => $this->user->id,
            'weight' => 5.5,
        ]);
    });

    test('user dapat melihat daftar pengambilan mereka sendiri.', function () {
        PickupRequest::factory()->count(3)->create(['user_id' => $this->user->id]);
        PickupRequest::factory()->create();

        $this->getJson('/api/pickups')
            ->assertStatus(200)
            ->assertJsonCount(3, 'data');
    });

    test('user dapat melihat detail pengambilan tertentu yang mereka miliki.', function () {
        $pickup = PickupRequest::factory()->create(['user_id' => $this->user->id]);

        $this->getJson("/api/pickups/{$pickup->id}")
            ->assertStatus(200)
            ->assertJsonPath('data.id', $pickup->id);
    });

    test('user tidak dapat melihat detail pengambilan dari user lain.', function () {
        $otherUserPickup = PickupRequest::factory()->create();

        $this->getJson("/api/pickups/{$otherUserPickup->id}")
            ->assertStatus(403);
    });

    test('user dapat melihat riwayat pengambilan mereka.', function () {
        PickupRequest::factory()->create(['user_id' => $this->user->id, 'status' => 'picked_up']);
        PickupRequest::factory()->create(['user_id' => $this->user->id, 'status' => 'rejected']);
        PickupRequest::factory()->create(['user_id' => $this->user->id, 'status' => 'pending']); // tidak boleh tampil

        $this->getJson('/api/pickups/history')
            ->assertStatus(200)
            ->assertJsonCount(2, 'data');
    });

    test('user dapat melihat statistiknya.', function () {
        PickupRequest::factory()->create(['user_id' => $this->user->id, 'status' => 'picked_up', 'weight' => 3.5]);
        PickupRequest::factory()->create(['user_id' => $this->user->id, 'status' => 'picked_up', 'weight' => 1.5]);

        $this->getJson('/api/pickups/statistics')
            ->assertStatus(200)
            ->assertJson([
                'data' => [
                    'total_weight_kg' => 5.0,
                    'points' => 50,
                ]
            ]);
    });

})->group('pickups', 'feature');
