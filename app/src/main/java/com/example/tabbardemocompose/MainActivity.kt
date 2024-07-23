package com.example.tabbardemocompose

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tabbardemocompose.ui.theme.TabBarDemoComposeTheme
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

private fun tabList(): List<Pair<String, Int>> {
    return listOf(
        "Round trip" to R.drawable.ic_round_trip,
        "One way" to R.drawable.ic_one_way,
        "Multi city" to R.drawable.ic_multi_city
    )
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            TabBarDemoComposeTheme {
                TabBarView()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TabBarView() {
    val context = LocalContext.current
    val window = (context as? Activity)?.window
    window?.statusBarColor = colorResource(id = R.color.primary).toArgb()
    val tabData = tabList()
    val pagerState = rememberPagerState(pageCount = { tabData.size })

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .statusBarsPadding(),
        topBar = {
            CenterAlignedTopAppBar(
                actions = {},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Transparent),
                title = {
                    Text(
                        text = "Book",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        textAlign = TextAlign.Center,
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .background(color = colorResource(id = R.color.primary))
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(color = colorResource(id = R.color.white))
                .padding(top = it.calculateTopPadding())
        ) {
            TabViewCompose(tabData, pagerState)
            Divider()
            ScrollablePagerContent(tabData, pagerState)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollablePagerContent(tabData: List<Pair<String, Int>>, pagerState: PagerState) {


    HorizontalPager(state = pagerState) { index ->
/*
        */

        when (index) {
            0 -> {

                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                        .fillMaxSize()

                ) {
                    LocationCompose()
                    Spacer(modifier = Modifier.height(18.dp))
                    DatePickerCompose()
                    Spacer(modifier = Modifier.height(18.dp))
                    PassengersAndClass()
                    Spacer(modifier = Modifier.height(18.dp))
                    RedeemView()
                    Spacer(modifier = Modifier.height(18.dp))
                    ButtonView()
                    Spacer(modifier = Modifier.height(18.dp))
                    CategoryList()
                    Spacer(modifier = Modifier.height(18.dp))
                    Text(text = "Your recent search",
                        color = Color.Black)

                }
            }

            1 -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = tabData[index].second),
                            contentDescription = null,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(text = tabData[index].first)
                    }
                }
            }

            2 -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = tabData[index].second),
                            contentDescription = null,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(text = tabData[index].first)
                    }
                }
            }
        }
    }

}

@Composable
fun CategoryList() {

    Row {

        Row {
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.white),
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .border(width = 1.dp, color = colorResource(id = R.color.divider))
                    .weight(1f)
                    .padding(horizontal = 5.dp)
                    .align(alignment = Alignment.CenterVertically)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_hotel),
                        modifier = Modifier.size(50.dp), contentDescription = null
                    )

                    Text(
                        text = "Hotel",
                    )
                }

            }

            Spacer(modifier = Modifier.width(10.dp))

            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.white),
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .border(width = 1.dp, color = colorResource(id = R.color.divider))
                    .weight(1f)
                    .padding(horizontal = 5.dp)
                    .align(alignment = Alignment.CenterVertically)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_hotel),
                        modifier = Modifier.size(50.dp), contentDescription = null
                    )

                    Text(
                        text = "Hotel",
                    )
                }

            }

            Spacer(modifier = Modifier.width(10.dp))
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.white),
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .border(width = 1.dp, color = colorResource(id = R.color.divider))
                    .weight(1f)
                    .padding(horizontal = 5.dp)
                    .align(alignment = Alignment.CenterVertically)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_hotel),
                        modifier = Modifier.size(50.dp), contentDescription = null
                    )

                    Text(
                        text = "Hotel",
                    )
                }

            }

            Spacer(modifier = Modifier.width(10.dp))
            Card(
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                ),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.white),
                ),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .border(width = 1.dp, color = colorResource(id = R.color.divider))
                    .weight(1f)
                    .padding(horizontal = 5.dp)
                    .align(alignment = Alignment.CenterVertically)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(10.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_hotel),
                        modifier = Modifier.size(50.dp), contentDescription = null
                    )

                    Text(
                        text = "Hotel",
                    )
                }

            }


        }


    }

}

@Composable
fun ButtonView() {

    TextButton(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.primary), shape = RectangleShape)
    ) {
        Text(
            text = "Book Now",
            color = colorResource(id = R.color.white),
            fontSize = 14.sp
        )
    }

}

@Composable
fun RedeemView() {
    var isSelected by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .clickable {
                isSelected = !isSelected
            }

    ) {

        Icon(
            painter = painterResource(
                id =
                if (isSelected) {
                    R.drawable.ic_filter_checked
                } else {
                    R.drawable.ic_filter_unchecked
                }
            ), contentDescription = null, tint = Color.Unspecified,
            modifier = Modifier
                .size(20.dp)
        )

        Text(
            text = "Redeem miles",
            modifier = Modifier.padding(start = 10.dp),
            color = Color.Black
        )


    }
}

@Composable
fun PassengersAndClass() {

    var expandedPassenger by remember { mutableStateOf(false) }
    var selectedItemPassenger by remember { mutableIntStateOf(1) }
    val passengersPassenger = listOf(1, 2, 3, 4, 5, 6)

    val classList = listOf("First", "Second", "Third")
    var expandedclass by remember { mutableStateOf(false) }
    var selectedItemclass by remember { mutableStateOf("First") }

    Row(modifier = Modifier.fillMaxWidth()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(text = "Passengers")
            Spacer(modifier = Modifier.height(10.dp))

            Column {
                // Button with dropdown arrow
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedPassenger = !expandedPassenger }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = selectedItemPassenger.toString(), modifier = Modifier.weight(1f),
                            color = colorResource(id = R.color.primary)
                        )
                        Icon(
                            imageVector = if (expandedPassenger) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = colorResource(id = R.color.primary)
                        )
                    }
                }

                // Dropdown menu
                DropdownMenu(
                    expanded = expandedPassenger,
                    onDismissRequest = { expandedPassenger = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                ) {
                    passengersPassenger.forEach { item ->
                        DropdownMenuItem(onClick = {
                            selectedItemPassenger = item
                            expandedPassenger = false
                        }, text = {
                            Text(
                                text = item.toString(),
                                color = colorResource(id = R.color.primary)
                            )
                        })
                    }
                }

                Divider(modifier = Modifier.padding(top = 5.dp))

            }
        }

        Spacer(modifier = Modifier.width(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(text = "Passengers")
            Spacer(modifier = Modifier.height(10.dp))

            Column {
                // Button with dropdown arrow
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedclass = !expandedclass }
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = selectedItemclass,
                            modifier = Modifier.weight(1f),
                            color = colorResource(id = R.color.primary)
                        )
                        Icon(
                            imageVector = if (expandedclass) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                            contentDescription = null,
                            tint = colorResource(id = R.color.primary)
                        )
                    }
                }

                // Dropdown menu
                DropdownMenu(
                    expanded = expandedclass,
                    onDismissRequest = { expandedclass = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp)
                ) {
                    classList.forEach { item ->
                        DropdownMenuItem(onClick = {
                            selectedItemclass = item
                            expandedclass = false
                        }, text = {
                            Text(text = item)
                        })
                    }
                }

                Divider(modifier = Modifier.padding(top = 5.dp))

            }
        }
    }
}

@Composable
fun DatePickerCompose() {

    var departDate by remember {
        mutableStateOf("08/05/2024")
    }

    var returnDate by remember {
        mutableStateOf("10/05/2024")
    }

    var isDepartDateShow by remember {
        mutableStateOf(false)
    }

    var isReturnDateShow by remember {
        mutableStateOf(false)
    }

    if (isReturnDateShow) {
        isReturnDateShow = false
        DatePick(selectedDate = {
            returnDate = it
        })
    }

    if (isDepartDateShow) {
        isDepartDateShow = false
        DatePick(selectedDate = {
            departDate = it
        })
    }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .clickable {
                    isDepartDateShow = true
                }
        ) {


            Column {
                Text(text = "Depart")
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = departDate, color = colorResource(id = R.color.primary),
                    modifier = Modifier.padding(bottom = 15.dp)
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = null,
                modifier = Modifier
                    .size(26.dp)
                    .padding(end = 5.dp)
                    .align(alignment = Alignment.CenterEnd),
                tint = colorResource(id = R.color.primary),
            )


            Divider(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)

            )
        }

        Spacer(modifier = Modifier.width(20.dp))

        Box(modifier = Modifier
            .weight(1f)
            .clickable {
                isReturnDateShow = true
            }) {
            Column {
                Text(text = "Return")
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = returnDate,
                    color = colorResource(id = R.color.primary),
                    modifier = Modifier.padding(bottom = 15.dp)
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = null,
                modifier = Modifier
                    .size(26.dp)
                    .padding(end = 5.dp)
                    .align(alignment = Alignment.CenterEnd),
                tint = colorResource(id = R.color.primary),
            )

            Divider(
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
                    .padding(top = 5.dp)
            )

        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabViewCompose(tabData: List<Pair<String, Int>>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        divider = {
            Spacer(modifier = Modifier.height(5.dp))
        },
        indicator = { tabPositions ->
            tabPositions.getOrNull(pagerState.currentPage)?.let {
                TabRowDefaults.Indicator(
                    modifier = Modifier
                        .tabIndicatorOffset(it)
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth(),
                    color = colorResource(id = R.color.primary)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        tabData.forEachIndexed { index, s ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = s.second), contentDescription = null,
                        tint = if (pagerState.currentPage == index) colorResource(id = R.color.primary) else colorResource(
                            id = R.color.secondary
                        ),
                        modifier = Modifier.size(22.dp)
                    )
                },
                text = {
                    Text(
                        text = s.first,
                        color = if (pagerState.currentPage == index) colorResource(id = R.color.primary) else colorResource(
                            id = R.color.secondary
                        )
                    )
                },
            )
        }
    }

}


@Composable
fun LocationCompose() {
    Row {
        Box(modifier = Modifier.weight(1f)) {
            LocationView("From")
        }
        Spacer(modifier = Modifier.width(20.dp))

        Box(modifier = Modifier.weight(1f)) {
            LocationView("To")
        }
    }
}

@Composable
fun LocationView(type: String) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
    ) {

        TextField(

            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = colorResource(id = R.color.divider),
            ),
            value = text,
            onValueChange = {
                text = it
            },
            label = {
                Text(
                    text = type,
                    textAlign = TextAlign.Start,
                    color = colorResource(id = R.color.secondary)
                )
            },
            placeholder = { Text(text = "DFA", color = colorResource(id = R.color.secondary)) },

            )

        Icon(
            painter = painterResource(id = R.drawable.ic_send), contentDescription = null,
            modifier = Modifier
                .size(26.dp)
                .padding(end = 5.dp)
                .align(alignment = Alignment.CenterEnd),
            tint = colorResource(id = R.color.primary),
        )
    }
}

@Composable
fun DatePick(selectedDate: (String) -> Unit) {

    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()
    val mDate = remember { mutableStateOf("") }
    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )
    mDatePickerDialog.show()

    mDatePickerDialog.setOnDismissListener {
        selectedDate(mDate.value)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TabBarDemoComposeTheme {
        TabBarView()
    }
}