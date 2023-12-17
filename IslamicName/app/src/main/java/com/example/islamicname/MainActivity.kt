package com.example.islamicname

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.islamicname.data.DataSource
import com.example.islamicname.model.Description
import com.example.islamicname.ui.theme.IslamicNameTheme
import com.example.islamicname.ui.theme.notoSerifBengali

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IslamicNameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    IslamicNameApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IslamicNameApp() {
    Scaffold(
        topBar = {
            IslamicNameTopAppBar()
        }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            BoyAndGirlButton()
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            DescriptionList(descriptionList = DataSource().loadAboutName())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IslamicNameTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                fontFamily = notoSerifBengali,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        },
        modifier = modifier
    )
}

@Composable
fun BoyAndGirlButton() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        CategoryCard(
            categoryIcon = R.drawable.ic_boy,
            categoryTitle = R.string.boy_name
        )
        CategoryCard(
            categoryIcon = R.drawable.ic_girl,
            categoryTitle = R.string.girl_name
        )
    }
}

@Composable
fun DescriptionList(descriptionList: List<Description>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(descriptionList) { description ->
            DescriptionItem(
                description = description,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun CategoryCard(categoryIcon: Int, categoryTitle: Int, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                painter = painterResource(id = categoryIcon),
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(id = R.dimen.image_size))
            )
            Text(
                text = stringResource(id = categoryTitle),
                fontFamily = notoSerifBengali,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        }
    }
}

@Composable
fun DescriptionItem(description: Description, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Text(
            text = LocalContext.current.getString(description.stringResourceId),
            fontFamily = notoSerifBengali,
            fontSize = 18.sp,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
        )
    }
}

@Preview
@Composable
fun IslamicNamePreview() {
    IslamicNameTheme(darkTheme = false) {
        IslamicNameApp()
    }
}

@Preview
@Composable
fun IslamicNameDarkThemePreview() {
    IslamicNameTheme(darkTheme = true) {
        IslamicNameApp()
    }
}
