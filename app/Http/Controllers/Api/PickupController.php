<?php
namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\PickupRequest;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class PickupController extends Controller
{
    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'waste_type_id' => 'required|exists:waste_types,id',
            'weight' => 'required|numeric|min:0.1'
        ]);

        if ($validator->fails()) {
            return response()->json(['success' => false, 'message' => $validator->errors()->first()], 422);
        }

        $pickup = PickupRequest::create([
            'user_id' => $request->user()->id,
            'waste_type_id' => $request->waste_type_id,
            'weight' => $request->weight,
        ]);

        return response()->json(['success' => true, 'message' => 'Permintaan penjemputan berhasil dibuat.', 'data' => $pickup]);
    }

    public function index(Request $request)
    {
        $pickups = PickupRequest::with('wasteType')->where('user_id', $request->user()->id)->get();
        return response()->json(['success' => true, 'data' => $pickups]);
    }

    public function statistics(Request $request)
    {
        $userId = $request->user()->id;

        $totalWeight = PickupRequest::where('user_id', $userId)->where('status', 'picked_up')->sum('weight');
        $points = $totalWeight * 10; // Misalnya 10 poin per 1 kg

        return response()->json([
                'success' => true,
                'data' => [
                'total_weight' => $totalWeight,
                'points' => $points
            ]
        ]);
    }
}
