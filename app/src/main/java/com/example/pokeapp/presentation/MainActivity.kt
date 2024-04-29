package com.example.pokeapp.presentation

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokeapp.R
import com.example.pokeapp.ui.theme.PokeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.ImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import com.example.pokeapp.R.color
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@AndroidEntryPoint
class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeAppTheme {
                val mainViewModel = hiltViewModel<MainViewModel>()
                val mainState by mainViewModel.mainState.collectAsState()
                Surface {
                    Scaffold(modifier = Modifier.fillMaxSize(),
                        topBar = {
                            OutlinedTextField(
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                value = mainState.searchString,
                                onValueChange = {
                                    mainViewModel.onEvent(
                                        MainUsEvents.onSearchWordChange(it)
                                    )
                                },
                                trailingIcon = {
                                    Icon(
                                        imageVector = Icons.Rounded.Search,
                                        contentDescription = stringResource(R.string.click_to_search),
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier
                                            .size(30.dp)
                                            .clickable {
                                                mainViewModel.onEvent(
                                                    MainUsEvents.OnSearchClick
                                                )
                                            }

                                    )
                                }
                            )

                        }
                    ) { paddingValues ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = paddingValues.calculateTopPadding())
                        ) {
                            MainScreen(mainState)
                        }

                    }
                }
            }
        }
    }

    @Composable
    fun MainScreen(mainState: MainState) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            mainState.pokeItem?.let { pokeItem ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(300.dp)
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    RoundedShape()
                    val imageUrl = pokeItem.sprites.other.home.front_default
                    Image(
                        alignment = Alignment.Center,
                        painter = rememberImagePainter(imageUrl),
                        contentDescription = null,
                    )
                }
                Row ( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = pokeItem.name,
                        fontSize = 30.sp
                    )
                    Text(
                        text = "Nº.${pokeItem.id.toString()}",
                        fontSize = 30.sp
                    )
                }
                Column(modifier = Modifier.padding(5.dp)) {
                    Row(){
                        pokeItem.types.forEach { type ->
                            type.Type.firstOrNull()?.let { typeList ->
                                Text(
                                    text = typeList.name,
                                    fontSize = 17.sp,
                                    modifier = Modifier
                                        .padding(4.dp)
                                )
                            }
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()){
                        Text(
                            text = "Peso: ${pokeItem.weight.toString()}hg",
                            fontSize = 17.sp,
                        )
                        Text(
                            text = "Altura: ${pokeItem.height.toString()}dm",
                            fontSize = 17.sp,
                        )
                    }
                    Column {
                        Text(
                            text = "Stats:",
                            fontSize = 30.sp,
                        )
                        pokeItem.stats.forEach { stat ->
                            val firstStatList = stat.stat.firstOrNull()
                            firstStatList?.let { statList ->
                                Spacer(modifier = Modifier.height(8.dp))
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "${statList.name}: ",
                                        fontSize = 17.sp,
                                    )
                                    LinearProgressIndicator(
                                        progress = stat.base_stat.toFloat() / 100,
                                        color = Color.Red,
                                        modifier = Modifier
                                            .padding(5.dp)
                                            .fillMaxWidth()
                                            .height(8.dp)
                                    )

                                }
                            }
                        }
                    }
                }

            }
        }
    }

    @Composable
    fun RoundedShape(
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(percent = 100))
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.Cyan)
        ) {
        }
    }


}

