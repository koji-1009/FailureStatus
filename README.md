# FailureStatus

A simple capsule class for Error handle with ViewModel and LiveData.

[![](https://jitpack.io/v/koji-1009/failurestatus.svg)](https://jitpack.io/#koji-1009/failurestatus)

## How to use

ViewModel.

```kotlin
class SampleViewModel: ViewModel {

    val user = MutableLiveData<User>()
    val failureStatus = MutableLiveData<FailureStatus>()

    fun fetchNewUser() {
        viewModelScope.launch {
            runCatching { 
                repository.fetchUserApi()
             }.fold (
                onSuccess = {
                    user.value = it
                },
                onFailure = {
                    failureStatus.value = FailureStatus(it)
                }
             )
        }
    }
}
```

Activity.

```kotlin
class SampleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this, R.layout.activity_main)

        val viewModel: SampleViewModel =  ViewModelProvider.get()
        viewModel.user.observe(this, Observer {
            // update new user data
        })
        viewModel.failureStatus.observe(this, Observer {
            val message = it.createMessage { throwable ->
                when (throwable) {
                    is IOException -> "Network error"
                    is RuntimeException -> "Report us"
                    else -> "Sorry, something wrong"
                }

                Toast.make(this, message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
```

### function `networkErrorBar` function

If you want show error message by `Snackbar`, use `networkErrorBar`!
see [FailureStatusExtension](https://github.com/koji-1009/FailureStatus/blob/master/failurestatus/src/main/java/com/app/dr1009/failurestatus/FailureStatusExtension.kt).

## Gradle

Step1. Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Step2. Add the dependency

```groovy
dependencies {
    implementation 'com.github.koji-1009:failurestatus:x.y.z'
}
```

## License

```
Copyright 2019 Koji Wakamiya

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
