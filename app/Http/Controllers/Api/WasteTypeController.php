<?php
namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Requests\Api\WasteTypeRequest;
use App\Models\WasteType;
use App\Traits\ApiResponse;

class WasteTypeController extends Controller
{
    use ApiResponse;

    public function index()
    {
        $wasteTypes = WasteType::with('category')->latest()->get();

        return $this->successResponse($wasteTypes);
    }

    public function store(WasteTypeRequest $request)
    {
        $wasteType = WasteType::create($request->validated());

        return $this->successResponse($wasteType, 'Jenis Sampah berhasil ditambahkan.', 201);
    }

    public function availableWasteTypes()
    {
        $wasteTypes = WasteType::with('category')->latest()->get();

        return $this->successResponse($wasteTypes);
    }
}
