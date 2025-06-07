<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Requests\Api\StorePickupRequest;
use App\Http\Resources\PickupRequestResource;
use App\Models\PickupRequest;
use App\Models\User;
use App\Models\WasteType;
use App\Traits\ApiResponse;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Throwable;

class PickupRequestController extends Controller
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

                return $pickupData->load('wasteType.category');
            });

            return $this->successResponse(
                new PickupRequestResource($pickup),
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
        $pickups = PickupRequest::where('user_id', $request->user()->id)
            ->with('wasteType.category', 'user')
            ->latest()
            ->get();

        return PickupRequestResource::collection($pickups);
    }

    public function show(PickupRequest $pickup)
    {
        $this->authorize('view', $pickup);
        return new PickupRequestResource($pickup->load('wasteType.category', 'user'));
    }

    public function updateStatus(Request $request, PickupRequest $pickup)
    {
        $this->authorize('update', $pickup);
        $validated = $request->validate(['status' => 'required|in:pending,picked_up,rejected']);
        $pickup->update($validated);
        return $this->successResponse(new PickupRequestResource($pickup), 'Status berhasil diperbarui.');
    }

    public function history(Request $request)
    {
        $history = PickupRequest::where('user_id', $request->user()->id)
            ->whereIn('status', ['picked_up', 'rejected'])
            ->with('wasteType.category')
            ->latest()
            ->get();
        return PickupRequestResource::collection($history);
    }

    public function statistics(Request $request)
    {
        $totalWeight = PickupRequest::where('user_id', $request->user()->id)
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

    private function createPickupForUser(User $user, WasteType $wasteType, array $data): PickupRequest
    {
        return PickupRequest::create([
            'user_id' => $user->id,
            'waste_type_id' => $wasteType->id,
            'weight' => $data['weight'],
            'status' => 'pending',
        ]);
    }
}
