package com.example.pengeluaranku.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pengeluaranku.ui.theme.PengeluarankuTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IncomePage(navController: NavController) {
    Column {
        Header(Color(0xFF48D971), navController)
        com.example.pengeluaranku.view.component.List()
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun IncomePagePreview() {
    PengeluarankuTheme {
        val navController = rememberNavController()
        IncomePage(navController)
    }
}