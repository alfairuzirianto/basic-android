package com.example.myunittest

class CuboidModel {
    private var width = 0.0
    private var length = 0.0
    private var height = 0.0

    fun getVolume(): Double = width * length * height

    fun getCircumference(): Double = 4 * (width + length + height)

    fun getSurfaceArea(): Double {
        return 2 * ((width * length) + (width * height) + (height * length))
    }

    fun save(width: Double, length: Double, height: Double) {
        this.width = width
        this.length = length
        this.height = height
    }
}