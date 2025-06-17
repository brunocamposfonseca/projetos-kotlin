package com.example.navband.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.navband.R

// Set of Material typography styles to start with
val Typography: Typography
    get() = Typography(
        bodyLarge = TextStyle(
            fontFamily = interFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        )
        /* Other default text styles to override
        titleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        labelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
        */
    )

val cambayFontFamily = FontFamily(
    Font(R.font.cambay_bold, FontWeight.Bold),
    Font(R.font.cambay_regular, FontWeight.Normal),
    Font(R.font.cambay_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.cambay_bolditalic, FontWeight.Bold, FontStyle.Italic)
)

val interFontFamily = FontFamily(
    Font(R.font.inter),
    Font(R.font.inter_italic, FontWeight.Normal, FontStyle.Italic)
)