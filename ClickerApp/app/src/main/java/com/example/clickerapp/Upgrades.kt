package com.example.clickerapp

sealed class UpgradeType{
    object ClickMultiplier: UpgradeType()
    object Autoclick: UpgradeType()
    object OfflineIncome: UpgradeType()
}

class Upgrade(
    type: UpgradeType,
    level: Int = 0,
    initialValue: Double,
    baseValue: Double,
    valueMultiplier: Double,
    baseCost: Double,
    costMultiplier: Double
){

}