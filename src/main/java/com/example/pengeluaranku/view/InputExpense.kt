package com.example.pengeluaranku.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pengeluaranku.ui.theme.PengeluarankuTheme
import com.example.pengeluaranku.view.component.DropdownInput
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


@Composable
fun InputExpense() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF758BFD))
            .padding(start = 25.dp,end = 25.dp, top = 90.dp, bottom = 25.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Button(
                onClick = { }, modifier = Modifier
                    .clip(RoundedCornerShape(25.dp))
                    .size(125.dp, 35.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                )
            ) {
                Text(text = "Use Receipt", color = Color(0xFF758BFD))
            }
        }
        NominalInput("Expense", Color.White)
        DropdownInput()
        InputDateTime(Color.White, Color.Black)
        InputNotes(Color.White, Color.Black)
        ButtonSubmit(Color.White, Color(0xFF758BFD))
    }
}

@Composable
fun NominalInput(textTitle: String, textColor: Color) {
    Column() {
        Text(
            text = "$textTitle :",
            fontSize = 22.sp,
            color = textColor,
            letterSpacing = 4.sp,
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            var text by remember { mutableStateOf(TextFieldValue("0")) }
            Text(
                text = "Rp.",
                fontSize = 36.sp,
                color = textColor,
                letterSpacing = 2.sp,
            )
            TextField(
                value = text,
                onValueChange = {
                    if (it.text.length <= 11) text = it
                },
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    textColor = textColor
                ),
                textStyle = TextStyle(
                    fontSize = 36.sp,
                    textAlign = TextAlign.End
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = NumberCommaTransformation(),
            )
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun InputDateTime(bgColor: Color, textColor: Color) {

    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()

    val selectedDate = remember { mutableStateOf(mCalendar.time) }

    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()

    val mDate = remember { mutableStateOf("") }

    val mDatePickerDialog = remember {
        DatePickerDialog(
            mContext,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                mDate.value = "$dayOfMonth/${month + 1}/$year"
                mCalendar.set(Calendar.YEAR, year)
                mCalendar.set(Calendar.MONTH, month)
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                selectedDate.value = mCalendar.time
            }, mYear, mMonth, mDay
        )
    }

    Column {
        Text(
            text = "Date & Time:",
            fontSize = 20.sp,
            letterSpacing = 1.5.sp,
            color = if (bgColor == Color(0xFF758BFD)) Color.Black else Color.White,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        TextField(
            value = SimpleDateFormat("dd/MMMM/yyyy").format(selectedDate.value),
            onValueChange = {},
            enabled = false,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp))
                .clickable { mDatePickerDialog.show() },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = bgColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                color = textColor,
                fontSize = 20.sp,
            ),
        )
    }
}

@Composable
fun InputNotes(bgColor: Color, textColor: Color) {

    var text by remember { mutableStateOf("") }

    Column() {
        Text(
            text = "Notes :",
            fontSize = 20.sp,
            letterSpacing = 1.5.sp,
            color = if (bgColor == Color(0xFF758BFD)) Color.Black else Color.White,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(15.dp)),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = bgColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(
                color = textColor,
                fontSize = 20.sp,
            ),
        )
    }
}

@Composable
fun ButtonSubmit(bgColor: Color, textColor: Color) {
    Box(modifier = Modifier
        .fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(
            onClick = { }, modifier = Modifier
                .clip(RoundedCornerShape(40.dp))
                .size(130.dp, 60.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = bgColor,
            )
        ) {
            Text(
                text = "Submit",
                fontSize = 18.sp,
                color = textColor,
                letterSpacing = 1.5.sp
            )
        }
    }
}


class NumberCommaTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            text = AnnotatedString(text.text.toLongOrNull().formatWithComma()),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return text.text.toLongOrNull().formatWithComma().length
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return text.length
                }
            }
        )
    }
}

fun Long?.formatWithComma(): String =
    NumberFormat.getNumberInstance(Locale("id", "ID")).format(this ?: 0)

@Preview(showBackground = true, device = Devices.PIXEL_4, showSystemUi = true)
@Composable
fun InputExpensePreview() {
    PengeluarankuTheme {
        InputExpense()
    }
}