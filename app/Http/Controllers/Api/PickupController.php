<?php
namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\PickupRequest;
use App\Models\WasteType;
use Illuminate\Http\Request; 
use Illuminate\Support\Facades\Validator;

class PickupController extends Controller
{
    public function store(Request $request)
{
    $validator = Validator::make($request->all(), [
        'waste_type_name' => 'required|string|max:255',
        'category_id' => 'required|exists:categories,id',
        'weight' => 'required|numeric|min:0.1'
    ]);

    if ($validator->fails()) {
        return response()->json([
            'success' => false,
            'message' => $validator->errors()->all()
        ], 422);
    }

    $wasteType = \App\Models\WasteType::firstOrCreate(
        ['name' => $request->waste_type_name],
        ['category_id' => $request->category_id, 'unit' => 'kg']
    );

    $pickup = PickupRequest::create([
        'user_id' => $request->user()->id,
        'waste_type_id' => $wasteType->id,   // <-- Ganti di sini
        'weight' => $request->weight,
        'status' => 'pending',
    ]);
    
    $pickup->load('wasteType.category');

    return response()->json([
        'success' => true,
        'message' => 'Permintaan penjemputan berhasil dibuat.',
        'data' => [
            'id' => $pickup->id,
            'user_id' => $pickup->user_id,
            'waste_type_id' => $pickup->waste_type_id,
            'waste_type_name' => $pickup->wasteType->name,
            'category_name' => $pickup->wasteType->category->name,
            'weight' => $pickup->weight,
            'status' => $pickup->status,
            'created_at' => $pickup->created_at,
            'updated_at' => $pickup->updated_at
        ]
    ]);
}


    public function index(Request $request)
{
    $pickups = PickupRequest::with(['wasteType.category', 'user'])
        ->where('user_id', $request->user()->id)
        ->get()
        ->map(function ($pickup) {
            return [
                'id' => $pickup->id,
                'user_name' => $pickup->user->name,
                'user_phone' => $pickup->user->phone,
                'waste_type' => $pickup->wasteType->name,
                'waste_category' => $pickup->wasteType->category->name,
                'weight' => $pickup->weight,
                'status' => $pickup->status,
                'requested_at' => $pickup->created_at->toDateTimeString(),
            ];
        });

    return response()->json([
        'success' => true,
        'data' => $pickups
    ]);
}


    public function statistics(Request $request)
    {
        $userId = $request->user()->id;

        $totalWeight = PickupRequest::where('user_id', $userId)
            ->where('status', 'picked_up')
            ->sum('weight');

        $points = $totalWeight * 10;

        return response()->json([
            'success' => true,
            'data' => [
                'total_weight' => $totalWeight,
                'points' => $points
            ]
        ]);
    }
}

