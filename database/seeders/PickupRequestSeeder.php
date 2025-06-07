<?php

namespace Database\Seeders;

use App\Models\PickupRequest;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class PickupRequestSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        PickupRequest::factory(5)->create();
    }
}
