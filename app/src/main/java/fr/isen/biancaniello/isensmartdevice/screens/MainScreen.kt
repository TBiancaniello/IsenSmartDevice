package fr.isen.biancaniello.isensmartdevice.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import fr.isen.biancaniello.isensmartdevice.R
import fr.isen.biancaniello.isensmartdevice.ScanActivity

@Composable
fun MainScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                colors = listOf(Color(0xFF64A9E4), Color(0xFF76BEFA))
            )) // Fond dégradé
            .padding(24.dp), // Espacement global
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {

        // Titre principal
        Text(
            text = "Bienvenue dans l'application de scan BLE",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 40.dp)
        )

        // Image du logo de l'application
        Image(
            painter = painterResource(id = R.drawable.ic_launcher2),
            contentDescription = "Logo de l'application",
            modifier = Modifier
                .size(200.dp)

        )

        // Description sous l'image
        Text(
            text = "Pour démarrer, appuyez sur 'Scanner les appareils BLE'.",
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // Bouton pour naviguer vers la page de scan
        Button(
            onClick = {
                val intent = Intent(context, ScanActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(56.dp), // Taille plus grande pour le bouton
            shape = RoundedCornerShape(50), // Bordure arrondie
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0077FF))
        ) {
            Text(
                text = "Scanner les appareils BLE",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        // Information de support (facultative)
        Text(
            text = "Assurez-vous que vos appareils BLE sont allumés et à portée.",
            fontSize = 14.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
