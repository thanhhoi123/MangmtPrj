<?php

namespace Database\Seeders;

use App\Models\User;
use App\Models\Category;
use App\Models\SanPham;
use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Seed the application's database.
     *
     * @return void
     */
    public function run()
    {
        User::insert([
            'name' => 'MQ',
            'email' => 'admin@example.com',
            'email_verified_at' => now(),
            'password' => '123456',
            'remember_token' => '1234567890',
            'role' => '1',
            'SDT' => '01234567891',
            'Address' => 'AAA at BBB',
            'avatar' => 'avatar/user1.png',
        ]);
        \App\Models\User::factory(20)->create();
        Category::insert(['name'=> 'BC']);
        Category::insert(['name'=> 'Trang']);
        Category::insert(['name'=> 'Si']);
        SanPham::factory(20)->create();
        $category = \App\Models\Category::all();
        SanPham::all()->each(function($sp) use($category)
        {
            $sp->categories()->attach($category->random(rand(1, 3))->pluck('id')->toArray());
        });
    }
}
