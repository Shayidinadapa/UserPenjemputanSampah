<?php

namespace App\Traits;

trait ApiResponse
{
    protected function successResponse($data, string $message = 'Success', int $statusCode = 200)
    {
        return response()->json([
            'message' => $message,
            'data' => $data,
        ], $statusCode);
    }

    protected function errorResponse(string $message = 'Error', int $statusCode = 400)
    {
        return response()->json([
            'message' => $message,
        ], $statusCode);
    }
}
