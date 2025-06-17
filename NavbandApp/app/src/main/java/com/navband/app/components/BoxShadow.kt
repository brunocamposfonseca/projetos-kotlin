import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.boxShadow(
    color: Color,
    offsetX: Dp = 4.dp,
    offsetY: Dp = 4.dp,
    blurRadius: Dp = 16.dp,
    cornerRadius: Dp = 20.dp,
    fullShadow: Boolean = false
): Modifier = this.then(
    Modifier.drawBehind {
        drawIntoCanvas { canvas ->
            val paint = androidx.compose.ui.graphics.Paint()
            val frameworkPaint = paint.asFrameworkPaint()

            frameworkPaint.color = color.copy(alpha = 0.1f).toArgb()
            frameworkPaint.setShadowLayer(
                blurRadius.toPx(),
                offsetX.toPx(),
                offsetY.toPx(),
                frameworkPaint.color
            )

            val left = if (fullShadow) 0f else 0f
            val top = if (fullShadow) 0f else 0f
            val right = if (fullShadow) size.width else size.width - offsetX.toPx()
            val bottom = if (fullShadow) size.height else size.height - offsetY.toPx()

            canvas.drawRoundRect(
                left,
                top,
                right,
                bottom,
                cornerRadius.toPx(),
                cornerRadius.toPx(),
                paint
            )
        }
    }
)


