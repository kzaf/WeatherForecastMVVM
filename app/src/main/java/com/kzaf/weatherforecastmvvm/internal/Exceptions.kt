package com.kzaf.weatherforecastmvvm.internal

import java.io.IOException

class NoConnectivityException: IOException()
class LocationPermissionNotGrantedException: Exception()
class DateNotFoundException: Exception()
