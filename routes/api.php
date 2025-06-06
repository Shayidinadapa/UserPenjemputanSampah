<?php

use App\Http\Controllers\Api\AuthController;
use App\Http\Controllers\Api\WasteController;
use App\Http\Controllers\Api\PickupController;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Api\CategoryController;

Route::post('/register', [AuthController::class, 'register']);
Route::post('/login', [AuthController::class, 'login']);
Route::post('/reset-password', [AuthController::class, 'resetPassword']);

Route::middleware('auth:sanctum')->group(function () {
    Route::post('/logout', [AuthController::class, 'logout']);

    Route::apiResource('/categories', CategoryController::class)->only(['index', 'store']);
    Route::apiResource('/waste-types', WasteController::class)->only(['index', 'store']);

    Route::get('/categories/{category}/waste-types', [WasteController::class, 'wasteTypes']);
    Route::get('/waste-types/available', [WasteController::class, 'availableWasteTypes']);

    Route::prefix('/pickups')->controller(PickupController::class)->group(function () {
        Route::post('/', 'Store');
        Route::get('/', 'index');
        Route::get('/{id}', 'show');
        Route::get('/history', 'history');
        Route::patch('/{id}/status', 'updateStatus');
        Route::get('/statistics', 'statistics');
    });

    Route::get('/user/statistics', [PickupController::class, 'statistics']);
});
