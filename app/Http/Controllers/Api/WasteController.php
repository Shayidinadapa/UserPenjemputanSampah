<?php
namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\WasteType;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class WasteController extends Controller
{
    public function index()
    {
        $wasteTypes = WasteType::with('category')->get();

        return response()->json([
            'success' => true,
            'data' => $wasteTypes
        ]);
    }
    
    public function store(Request $request)
    {
        $validator = Validator::make($request->all(), [
            'category_id' => 'required|exists:categories,id',
            'name' => 'required|string|max:255',
            'unit' => 'required|string|max:50',
        ]);

        if ($validator->fails()) {
            return response()->json(['success' => false, 'message' => $validator->errors()->first()], 422);
        }

        $wasteType = WasteType::create([
            'category_id' => $request->category_id,
            'name' => $request->name,
            'unit' => $request->unit,
        ]);

        return response()->json([
            'success' => true,
            'message' => 'Jenis Sampah berhasil ditambahkan.',
            'data' => $wasteType
        ], 201);
    }

    
}
