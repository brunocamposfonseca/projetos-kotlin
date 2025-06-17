package com.navband.app.services

import android.preference.PreferenceManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.core.content.ContextCompat
import com.navband.app.R
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.BoundingBox
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import com.navband.app.screens.pages.CoordList

@Composable
fun MapScreen(info: CoordList?, map_state: () -> Unit) {
    val context = LocalContext.current
    if (info == null) return;
    val startPoint = remember { info.origem }
    val endPoint = remember { info.destino }
    val nomeCrianca = remember { info.nome }

    val mapView = remember {
        MapView(context).apply {
            Configuration.getInstance().load(
                context,
                PreferenceManager.getDefaultSharedPreferences(context)
            )
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)

            // Polyline
            val polyline = Polyline().apply {
                setPoints(listOf(startPoint, endPoint))
                color = android.graphics.Color.BLACK
                width = 8f
            }
            overlays.add(polyline)

            // Markers
            overlays.add(Marker(this).apply {
                position = startPoint
                title = "Você"
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                icon = ContextCompat.getDrawable(context, R.drawable.current_location_marker)
            })
            overlays.add(Marker(this).apply {
                position = endPoint
                title = nomeCrianca
                setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                icon = ContextCompat.getDrawable(context, R.drawable.point_marker)
            })

            post {
                zoomToBoundingBox(BoundingBox.fromGeoPoints(listOf(startPoint, endPoint)), true, 80)
            }
        }
    }

    val distanceInMeters = remember(startPoint, endPoint) {
        startPoint.distanceToAsDouble(endPoint)
    }

    val formatted = remember(distanceInMeters) {
        when {
            distanceInMeters >= 1000 -> "%.2f km".format(distanceInMeters / 1000.0)
            distanceInMeters >= 1 -> "%.2f m".format(distanceInMeters)
            else -> "%.2f cm".format(distanceInMeters * 100)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        // Card com desfoque e texto de distância
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            // Fundo com blur
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .blur(16.dp)
                    .background(Color.White.copy(alpha = 0.6f))
            )

            // Conteúdo visível
            Row(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = "Distância: ",
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = formatted,
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Mapa ocupa fundo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(0f) // <-- mapa fica atrás
        ) {
            AndroidView(
                factory = { context -> mapView },
                modifier = Modifier
                    .weight(1f)
                    .clipToBounds()
                    .zIndex(1f)
            )
        }

        // Botão inferior
        Button(
            onClick = { map_state() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
                .zIndex(2f), // botão por cima de tudo
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Trocar de Pulseira")
        }
    }
}

