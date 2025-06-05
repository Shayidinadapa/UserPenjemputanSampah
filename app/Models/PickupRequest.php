<?php
namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class PickupRequest extends Model
{
    use HasFactory;

    protected $fillable = ['user_id', 'waste_type_id', 'weight', 'status'];

    public function user()
    {
        return $this->belongsTo(User::class);
    }

    public function wasteType()
    {
        return $this->belongsTo(WasteType::class);
    }
}

