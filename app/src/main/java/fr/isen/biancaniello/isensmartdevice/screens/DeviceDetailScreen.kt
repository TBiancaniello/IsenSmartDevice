package fr.isen.biancaniello.isensmartdevice.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceDetailScreen(
    name: String,
    address: String,
    rssi: Int,
    onBack: () -> Unit,
    onConnectClick: () -> Unit,
    connectionStatus: String,
    isConnected: Boolean,
    ledStates: List<Boolean>,
    onLedToggle: (Int) -> Unit,
    isSubscribedButton1: Boolean,
    isSubscribedButton3: Boolean,
    onSubscribeToggleButton1: (Boolean) -> Unit,
    onSubscribeToggleButton3: (Boolean) -> Unit,
    counterButton1: Int,
    counterButton3: Int,
    onResetCounter: () -> Unit
) {

    val ledColors = listOf(
        Color(0xFF80D0FF),
        Color(0xFFB2F7A0),
        Color(0xFFF9A1A1)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AndroidSmartDevice") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Retour", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF76BEFA),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFF76BEFA)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isConnected) {
                Spacer(modifier = Modifier.height(32.dp))


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White) // Fond blanc pour la Card
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {
                        Text("Périphérique détecté", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Nom de l'appareil : $name", fontSize = 16.sp)
                        Text("Adresse MAC : $address", fontSize = 14.sp, color = Color.Gray)
                        Text("Signal RSSI : $rssi dBm", fontSize = 14.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(16.dp))
                        Text("État de la connexion : $connectionStatus", fontSize = 14.sp, color = Color(0xFFB71C1C))
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = onConnectClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) {
                    Text("Se connecter au périphérique", color = Color.White, fontSize = 16.sp)
                }
            }
            else {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Contrôlez vos LEDs à distance! ", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))



                Column(
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ledStates.forEachIndexed { index, isOn ->
                        val color = ledColors.getOrNull(index) ?: Color.Gray
                        Button(
                            onClick = { onLedToggle(index) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isOn) color else Color.LightGray
                            ),
                            modifier = Modifier
                                .height(64.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = "LED ${index + 1}",
                                color = Color.White,
                                maxLines = 1
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Divider(modifier = Modifier.padding(horizontal = 16.dp))
                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isSubscribedButton3,
                        onCheckedChange = { onSubscribeToggleButton3(it) }
                    )
                    Text("Abonnement aux notifications du bouton 1")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isSubscribedButton1,
                        onCheckedChange = { onSubscribeToggleButton1(it) }
                    )
                    Text("Abonnement aux notifications du bouton 3")
                }

                Spacer(modifier = Modifier.height(24.dp))
                Text("Compteur du bouton 1 : $counterButton3", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text("Compteur du bouton 3 : $counterButton1", fontSize = 16.sp)

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onResetCounter,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
                ) {
                    Text("Réinitialiser les compteurs", color = Color.White)
                }
            }
        }
    }
}
