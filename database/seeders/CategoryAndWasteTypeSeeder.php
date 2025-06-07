<?php

namespace Database\Seeders;

use App\Models\Category;
use App\Models\WasteType;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class CategoryAndWasteTypeSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        $data = [
            'Komputer & Laptop' => [
                ['name' => 'Monitor', 'unit' => 'kg'],
                ['name' => 'CPU', 'unit' => 'kg'],
                ['name' => 'Keyboard', 'unit' => 'kg'],
            ],
            'Telepon & Gadget' => [
                ['name' => 'Smartphone', 'unit' => 'kg'],
                ['name' => 'Tablet', 'unit' => 'kg'],
                ['name' => 'Charger', 'unit' => 'kg'],
            ],
            'Elektronik Rumah Tangga' => [
                ['name' => 'TV', 'unit' => 'kg'],
                ['name' => 'Kulkas', 'unit' => 'kg'],
                ['name' => 'Mesin Cuci', 'unit' => 'kg'],
            ],
        ];

        foreach ($data as $categoryName => $wasteTypes) {
            $category = Category::create(['name' => $categoryName]);
            foreach ($wasteTypes as $waste) {
                WasteType::create([
                    'category_id' => $category->id,
                    'name' => $waste['name'],
                    'unit' => $waste['unit'],
                ]);
            }
        }
    }
}
