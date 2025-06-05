<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

class AuthController extends Controller
{
    public function register(Request $request)
    {
        return response()->json(['message' => 'register success']);
    }

    public function login(Request $request)
    {
        return response()->json(['message' => 'login success']);
    }
}
