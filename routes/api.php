<?php

use App\Http\Controllers\Api\AuthController;
use App\Http\Controllers\Api\WasteTypeController;
use App\Http\Controllers\Api\PickupRequestController;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\Api\CategoryController;

Route::post('/register', [AuthController::class, 'register']);
Route::post('/login', [AuthController::class, 'login']);
Route::post('/reset-password', [AuthController::class, 'resetPassword']);

Route::middleware('auth:sanctum')->group(function () {
    Route::post('/logout', [AuthController::class, 'logout']);

    Route::apiResource('/categories', CategoryController::class)->only(['index', 'store']);
    Route::apiResource('/waste-types', WasteTypeController::class)->only(['index', 'store']);

    Route::get('/categories/{category}/waste-types', [WasteTypeController::class, 'wasteTypes']);
    Route::get('/waste-types/available', [WasteTypeController::class, 'availableWasteTypes']);

    Route::prefix('/pickups')->controller(PickupRequestController::class)->group(function () {
        Route::get('/history', 'history');
        Route::get('/statistics', 'statistics');
        Route::get('/', 'index');
        Route::post('/', 'Store');
        Route::get('/{pickup}', 'show');
        Route::patch('/{pickup}/status', 'updateStatus');
    });

    Route::get('/user/statistics', [PickupRequestController::class, 'statistics']);
});
