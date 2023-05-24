package com.example.pengeluaranku.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pengeluaranku.ui.theme.PengeluarankuTheme

@Composable
fun InputIncome() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp,end = 25.dp, top = 120.dp, bottom = 25.dp)
    ) {
        NominalInput("Income", Color.Black)
        InputDateTime(Color(0xFF758BFD),Color.White)
        InputNotes(Color(0xFF758BFD), Color.White)
        ButtonSubmit(bgColor = Color(0xFF758BFD),Color.White)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun InputIncomePreview() {
    PengeluarankuTheme {
        InputIncome()
    }
}