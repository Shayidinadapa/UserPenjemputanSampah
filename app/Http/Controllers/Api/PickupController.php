<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Requests\PickupRequest as StorePickupRequest;
use App\Http\Resources\PickupResource;
use App\Models\Pickup;
use App\Models\User;
use App\Models\WasteType;
use App\Traits\ApiResponse;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Throwable;

class PickupController extends Controller
{
    use ApiResponse;
    use AuthorizesRequests;

    public function store(StorePickupRequest $request)
    {
        try {
            $pickup = DB::transaction(function () use ($request) {
                $validatedData = $request->validated();
                $user = $request->user();

                $wasteType = $this->findOrCreateWasteType($validatedData);
                $pickupData = $this->createPickupForUser($user, $wasteType, $validatedData);

                return $pickupData;
            });

            return $this->successResponse(
                new PickupResource($pickup->load('wasteType.category')),
                'Permintaan penjemputan berhasil dibuat.',
                201
            );
        } catch (Throwable $e) {
            report($e);
            return $this->errorResponse('Gagal memproses permintaan.', 500);
        }
    }

    public function index(Request $request)
    {
        $pickups = Pickup::where('user_id', $request->user()->id)
            ->with('wasteType.category', 'user')
            ->latest()
            ->get();

        return PickupResource::collection($pickups);
    }

    public function show(Pickup $pickup)
    {
        $this->authorize('view', $pickup);
        return new PickupResource($pickup->load('wasteType.category', 'user'));
    }

    public function updateStatus(Request $request, Pickup $pickup)
    {
        $this->authorize('update', $pickup);
        $validated = $request->validate(['status' => 'required|in:pending,picked_up,rejected']);
        $pickup->update($validated);
        return $this->successResponse(new PickupResource($pickup), 'Status berhasil diperbarui.');
    }

    public function history(Request $request)
    {
        $history = Pickup::where('user_id', $request->user()->id)
            ->whereIn('status', ['picked_up', 'rejected'])
            ->with('wasteType.category')
            ->latest()
            ->get();
        return PickupResource::collection($history);
    }

    public function statistics(Request $request)
    {
        $totalWeight = Pickup::where('user_id', $request->user()->id)
            ->where('status', 'picked_up')
            ->sum('weight');

        $stats = [
            'total_weight_kg' => round($totalWeight, 2),
            'points' => $totalWeight * 10,
        ];

        return $this->successResponse($stats);
    }


    // ================================
    // PRIVATE METHODS - Logika Bisnis
    // ================================

    private function findOrCreateWasteType(array $data): WasteType
    {
        return WasteType::firstOrCreate(
            ['name' => $data['waste_type_name']],
            ['category_id' => $data['category_id'], 'unit' => 'kg']
        );
    }

    private function createPickupForUser(User $user, WasteType $wasteType, array $data): Pickup
    {
        return Pickup::create([
            'user_id' => $user->id,
            'waste_type_id' => $wasteType->id,
            'weight' => $data['weight'],
            'status' => 'pending',
        ]);
    }
}
