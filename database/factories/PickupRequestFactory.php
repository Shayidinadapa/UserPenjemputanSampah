<?php

namespace Database\Factories;

use App\Models\User;
use App\Models\WasteType;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Pickup>
 */
class PickupRequestFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            "user_id" => User::factory(),
            "waste_type_id" => WasteType::factory(),
            'weight' => fake()->randomFloat(null, 0, 10),
            "status" => fake()->randomElement(['pending', 'picked_up', 'rejected']),
        ];
    }
}
