<?php
namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\Category;

class WasteController extends Controller
{
    public function categories()
    {
        $categories = Category::all();
        return response()->json([
            'success' => true,
            'data' => $categories
        ]);
    }

    public function wasteTypes($categoryId)
    {
        $category = Category::with('wasteTypes')->find($categoryId);
        if (!$category) {
            return response()->json(['success' => false, 'message' => 'Kategori tidak ditemukan.'], 404);
        }

        return response()->json([
            'success' => true,
            'data' => $category->wasteTypes
        ]);
    }
}
