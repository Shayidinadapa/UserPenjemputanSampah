<?php

namespace Database\Factories;

use App\Models\Category;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\WasteType>
 */
class WasteTypeFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        $wasteData = [
            'Komputer & Laptop' => ['Monitor', 'CPU', 'Keyboard', 'Mouse'],
            'Telepon & Gadget' => ['Smartphone', 'Tablet', 'Charger', 'Earphone'],
            'Elektronik Rumah Tangga' => ['TV', 'Mesin Cuci', 'Kulkas', 'Blender']
        ];

        $categoryName = $this->faker->randomElement(array_keys($wasteData));

        return [
            "category_id" => Category::factory(),
            "name" => fake()->randomElement($wasteData[$categoryName]),
            "unit"=> "kg",
        ];
    }
}
