# Bottom Navigation in Android

This README provides guidance on implementing Bottom Navigation in an Android application using Java or Kotlin.

## Features
- Easily switch between multiple fragments or activities.
- Responsive design with material design aesthetics.
- Supports up to five primary destinations.

## Prerequisites
1. Android Studio installed.
2. Familiarity with XML layouts and fragments.
3. Basic knowledge of Android development in Java or Kotlin.

## Dependencies
Ensure that you include the Material Components library in your `build.gradle` file:

```groovy
dependencies {
    implementation "com.google.android.material:material:1.9.0"
}
```

## Implementation Steps

### 1. Add Bottom Navigation to Your Layout
In your `activity_main.xml` file, include the `BottomNavigationView`:

```xml
<com.google.android.material.bottomnavigation.BottomNavigationView
    android:id="@+id/bottom_navigation"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_gravity="bottom"
    app:menu="@menu/bottom_nav_menu"
    app:itemIconTint="@color/selector_bottom_nav" 
    app:itemTextColor="@color/selector_bottom_nav"
    app:labelVisibilityMode="labeled" />
```

### 2. Create a Menu Resource
Create a menu resource file `res/menu/bottom_nav_menu.xml` for the navigation items:

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/home"
        android:icon="@drawable/ic_home"
        android:title="Home" />

    <item
        android:id="@+id/search"
        android:icon="@drawable/ic_search"
        android:title="Search" />

    <item
        android:id="@+id/profile"
        android:icon="@drawable/ic_profile"
        android:title="Profile" />
</menu>
```

### 3. Handling Bottom Navigation Clicks
In your `MainActivity`, set up the `BottomNavigationView` and attach click listeners.

#### Java:
```java
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    // Load Home Fragment
                    loadFragment(new HomeFragment());
                    return true;

                case R.id.search:
                    // Load Search Fragment
                    loadFragment(new SearchFragment());
                    return true;

                case R.id.profile:
                    // Load Profile Fragment
                    loadFragment(new ProfileFragment());
                    return true;
            }
            return false;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
```

#### Kotlin:
```kotlin
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.search -> {
                    loadFragment(SearchFragment())
                    true
                }
                R.id.profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // Set default fragment
        loadFragment(HomeFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
```

### 4. Create Fragments
Create fragment classes like `HomeFragment`, `SearchFragment`, and `ProfileFragment` with associated layout files (e.g., `fragment_home.xml`).

#### Example:
```kotlin
class HomeFragment : Fragment(R.layout.fragment_home)
```

### 5. Add a Fragment Container
In `activity_main.xml`, add a container for fragments:

```xml
<FrameLayout
    android:id="@+id/fragment_container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:layout_above="@id/bottom_navigation" />
```

## Customization
- **Icon Colors**: Define `res/color/selector_bottom_nav.xml`:

```xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:color="@color/primary" android:state_checked="true" />
    <item android:color="@color/gray" android:state_checked="false" />
</selector>
```

- **Add Badges**: Use `BadgeDrawable` to show notifications:

#### Kotlin Example:
```kotlin
val badge = bottomNavigationView.getOrCreateBadge(R.id.profile)
badge.isVisible = true
badge.number = 5
```

## Screenshots
Include example screenshots of your Bottom Navigation setup.

## License
This project is licensed under the MIT License. See the LICENSE file for details.

## Contribution
Feel free to fork this repository and submit pull requests. All contributions are welcome!
