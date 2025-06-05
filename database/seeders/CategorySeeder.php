<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Category;
use App\Models\WasteType;

class CategorySeeder extends Seeder
{
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
