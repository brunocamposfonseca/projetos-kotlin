    package com.navband.app.ui.theme

    import android.os.Build
    import androidx.compose.foundation.isSystemInDarkTheme
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.darkColorScheme
    import androidx.compose.material3.dynamicDarkColorScheme
    import androidx.compose.material3.dynamicLightColorScheme
    import androidx.compose.material3.lightColorScheme
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.platform.LocalContext

    private val DarkColorScheme = darkColorScheme(
        primary = PrimaryDark,
        onSurface = BLACK,
        secondary = SecondaryDark,
        background = BackgroundDark,
        error = AlertAccent,
        inverseOnSurface = TextDark
    )

    private val LightColorScheme = lightColorScheme(
        primary = PrimaryLight,
        onSurface = WHITE,
        secondary = SecondaryLight,
        background = BackgroundLight,
        error = AlertAccent,
        inverseOnSurface = TextLight
    )

    @Composable
    fun NavbandAppTheme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        dynamicColor: Boolean = false,
        content: @Composable () -> Unit
    ) {
        val colorScheme = when {
            dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val context = LocalContext.current
                if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
            }

            darkTheme -> DarkColorScheme
            else -> LightColorScheme
        }

        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }