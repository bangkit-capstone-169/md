package com.example.pengeluaranku.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pengeluaranku.ui.theme.PengeluarankuTheme
import com.example.pengeluaranku.view.component.BottomNavigation
import com.example.pengeluaranku.view.component.NavigationGraph
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Pengeluaranku() {
    val navController = rememberNavController()
    var currentRoute by remember { mutableStateOf("") }

    DisposableEffect(navController) {
        val observer = NavController.OnDestinationChangedListener { _, destination, _ ->
            currentRoute = destination.route?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            } ?: ""
        }
        navController.addOnDestinationChangedListener(observer)

        onDispose {
            navController.removeOnDestinationChangedListener(observer)
        }
    }


    val title = when (currentRoute) {
        "Dashboard" -> "Pengeluaranku"
        "InputExpense" -> "Input Expense"
        "InputIncome" -> "Input Income"
        else -> currentRoute
    }

    val titleAlign = if (currentRoute == "InputExpense" || currentRoute == "InputIncome") Modifier.padding(start = 60.dp) else Modifier.fillMaxWidth()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            navigationIcon = {
                if (currentRoute == "InputExpense" || currentRoute == "InputIncome") {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "Back Icon Button", tint = Color.White)
                    }
                } else {
                    null
                }
            },
            title = {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 3.5.sp
                    )
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = if (currentRoute != "Dashboard") Color(
                    0xFF758BFD
                ) else Color.Black.copy(0.3f),
                titleContentColor = Color.White,
            ),
        )
    }, bottomBar = {
        if (currentRoute == "InputExpense" || currentRoute == "InputIncome") null else BottomNavigation(
            navController = navController
        )
    }) {
        NavigationGraph(navController = navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_4)
@Composable
fun PengeluarankuPreview() {
    PengeluarankuTheme {
        Pengeluaranku()
    }
}