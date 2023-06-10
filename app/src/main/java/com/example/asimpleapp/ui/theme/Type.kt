package com.example.asimpleapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.asimpleapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
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
val JetBrainsMonoNerdFont = FontFamily(
    Font(R.font.jetbrainsmononerdfont_regular, FontWeight.Normal),
    Font(R.font.jetbrainsmononerdfont_bold, FontWeight.Bold),
    Font(R.font.jetbrainsmononerdfont_extrabold, FontWeight.ExtraBold),
    Font(R.font.jetbrainsmononerdfont_light, FontWeight.Light),
    Font(R.font.jetbrainsmononerdfont_extralight, FontWeight.ExtraLight),
    Font(R.font.jetbrainsmononerdfont_thin, FontWeight.Thin),
    Font(R.font.jetbrainsmononerdfont_thin, FontWeight.Thin),
)
val JetBrainsMonoTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    displayLarge = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    displayMedium = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    displaySmall = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    headlineLarge = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    headlineMedium = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    headlineSmall = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    titleLarge = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    titleMedium = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    titleSmall = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    bodyMedium = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    bodySmall = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    labelLarge = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    labelMedium = TextStyle(
        fontFamily = JetBrainsMonoNerdFont,
    ),
    labelSmall = TextStyle(
        fontFamily = JetBrainsMonoNerdFont
    ),
)