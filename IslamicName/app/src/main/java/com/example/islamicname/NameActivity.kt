package com.example.islamicname

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.islamicname.data.BoyName
import com.example.islamicname.model.Name
import com.example.islamicname.ui.theme.IslamicNameTheme
import com.example.islamicname.ui.theme.notoSerifBengali

class NameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IslamicNameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameList(names: List<Name>) {
    Scaffold(
        topBar = {
            ListTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it)
        {
            items(names) {
                NameItem(
                    name = it,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.boy_name),
                fontFamily = notoSerifBengali,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        },
        modifier = modifier
    )
}


@Composable
fun NameItem(name: Name, modifier: Modifier = Modifier) {

    var expanded by remember { mutableStateOf(false) }

    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                .padding(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_medium))
            ) {
                Text(
                    text = name.name,
                    fontFamily = notoSerifBengali,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.weight(.2f))
                NameItemButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded })
            }
            if (expanded) {
                NameDetails(
                    nameMeaning = name.meaning,
                    nameEnglish = name.english,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_small),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
                )
            }
        }
    }
}

@Composable
fun NameItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
            contentDescription = stringResource(id = R.string.expand_button_content_description),
            tint = MaterialTheme.colorScheme.secondary,
        )
    }
}

@Composable
fun NameDetails(nameMeaning: String, nameEnglish: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.name_meaning, nameMeaning),
            fontFamily = notoSerifBengali,
            fontSize = 18.sp
        )
        Text(
            text = stringResource(id = R.string.name_english, nameEnglish),
            fontFamily = notoSerifBengali,
            fontSize = 18.sp
        )
    }
}

@Preview
@Composable
fun NameListPreview() {
    IslamicNameTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NameList(names = BoyName().loadBoyName())
        }
    }
}

