<?php

use App\Http\Controllers\Api\AuthController;
use App\Http\Controllers\Api\WasteController;
use App\Http\Controllers\Api\PickupController;
use Illuminate\Support\Facades\Route;
use App\Http\Middleware\AdminMiddleware;
use App\Http\Controllers\CategoryController;



Route::post('/register', [AuthController::class, 'register']);
Route::post('/login', [AuthController::class, 'login']);
Route::post('/reset-password', [AuthController::class, 'resetPassword']);



Route::middleware('auth:sanctum')->get('/pickup/history', [PickupController::class, 'history']);
Route::middleware('auth:sanctum')->put('/pickup/{id}/status', [PickupController::class, 'updateStatus']);
Route::middleware('auth:sanctum')->get('/pickup/statistics', [PickupController::class, 'statistics']);
Route::middleware('auth:sanctum')->get('/categories', [CategoryController::class, 'index']);
Route::middleware('auth:sanctum')->post('/categories', [WasteController::class, 'store']);
Route::middleware(['auth:sanctum'])->group(function () {
    // route untuk user & admin

    // route khusus admin
    Route::post('/categories', [CategoryController::class, 'store'])
        ->middleware(AdminMiddleware::class);
});

Route::middleware('auth:sanctum')->group(function () {
    Route::post('/logout', [AuthController::class, 'logout']);
    Route::post('/pickups', [PickupController::class, 'store']);

    Route::get('/waste-types', [WasteController::class, 'index']);
    Route::post('/waste-types', [WasteController::class, 'store']);
    Route::get('/waste-types', [WasteController::class, 'availableWasteTypes']);

    Route::get('/categories', [CategoryController::class, 'index']);
    Route::get('/categories', [WasteController::class, 'categories']);
    Route::get('/categories/{id}/waste-types', [WasteController::class, 'wasteTypes']);
    Route::get('/pickups/{id}', [PickupController::class, 'show']);
    Route::get('/pickups', [PickupController::class, 'index']);
    Route::get('/user/statistics', [PickupController::class, 'statistics']);
});
