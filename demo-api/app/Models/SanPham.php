<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class SanPham extends Model
{
    use HasFactory;

    protected $guarded = [];

    public function Category()
    {
        return $this->belongsToMany(Category::class);
    }
    public function Donhangs()
    {
        return $this->belongsToMany(Donhangs::class());
    }
}
