package com.example.pengeluaranku.view.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pengeluaranku.ui.theme.PengeluarankuTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun List() {
    val itemList = listOf(
        ItemGroup("Group 1", listOf("Item 1", "Item 2", "Item 3")),
        ItemGroup("Group 2", listOf("Item 4", "Item 5")),
        ItemGroup("Group 3", listOf("Item 6", "Item 7", "Item 8", "Item 9"))
    )

    LazyColumn {
        itemList.forEach { group ->
            item {
                HeaderItem(item = group.title)
            }
            items(group.items) {item ->
                ListItem(item = item)
                Divider(
                    color = Color.LightGray,
                    thickness = 2.dp,
                    modifier = Modifier.padding(5.dp, 3.dp)
                )
            }
        }
    }
}

@Composable
fun HeaderItem(item: String) {
    Text(
        text = "Header $item",
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp,16.dp,10.dp,16.dp),
        fontWeight = FontWeight.Light,
        fontSize = 24.sp,
        letterSpacing = 1.sp
    )
}

@Composable
fun ListItem(item: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {
            Text(text = item, fontSize = 20.sp, letterSpacing = 3.sp)
            Text(
                text = item,
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                letterSpacing = 4.sp
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(text = item, fontSize = 20.sp, letterSpacing = 3.sp)
            Text(
                text = "Sub$item",
                fontWeight = FontWeight.Light,
                fontSize = 16.sp,
                letterSpacing = 4.sp
            )
        }
    }
}

data class ItemGroup(val title: String, val items: List<String>)


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun DashboardPreview() {
    PengeluarankuTheme {
        List()
    }
}
