package com.example.asimpleapp.ui.theme

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.io.BufferedReader
import java.io.InputStreamReader

/* Kotlin data/model classes that map the JSON response, we could also add Moshi
 * annotations to help the compiler with the mappings on a production app */
data class UserResponse(val results: List<User>)
data class User(val email: String, val phone: String)

/* Retrofit service that maps the different endpoints on the API, you'd create one
 * method per endpoint, and use the @Path, @Query and other annotations to customize
 * these at runtime */
interface UserService {
    @GET("/api")
    fun getUsers(): Call<UserResponse>
}

fun getCurl(){
    /* Creates an instance of the UserService using a simple Retrofit builder using Moshi
     * as a JSON converter, this will append the endpoints set on the UserService interface
     * (for example '/api', '/api?results=2') with the base URL set here, resulting on the
     * full URL that will be called: 'https://randomuser.me/api' */
    val service = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(UserService::class.java)

    /* Calls the endpoint set on getUsers (/api) from UserService using enqueue method
     * that creates a new worker thread to make the HTTP call */
    service.getUsers().enqueue(object : Callback<UserResponse> {

        /* The HTTP call failed. This method is run on the main thread */
        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            Log.d("TAG_", "An error happened!")
            t.printStackTrace()
        }

        /* The HTTP call was successful, we should still check status code and response body
         * on a production app. This method is run on the main thread */
        override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
            /* This will print the response of the network call to the Logcat */
            Log.d("TAG_", response.body().toString())
        }
    })
}

// Custom app bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    currentScreen: String = "A",
    canNavBack: Boolean = false,
    addText: String = "",
    navUp: () -> Unit = {}
){
    TopAppBar(
        title = {
            if (addText == "" || addText == "start")
                Row {
                    Text(text = "Hi there")
                }
            else
                Text(addText)
                },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavBack){
                IconButton(onClick = navUp) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go back")
                }
            }
        }
    )
}

// First screen, which contains each and every screen of the app
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppEntry(){
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            AppBar(
                currentScreen = "Start",
                canNavBack = navController.previousBackStackEntry != null,
                navUp = { navController.navigateUp() },
                addText = backStackEntry?.destination?.route ?: ""
            )
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "start",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("start") {
                StartScreen(navController)
            }
            composable("A") {
                ScreenA(navController)
            }
            composable("B") {
                ScreenB(navController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(navController: NavController){
    val scrollPos = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(10.dp, 0.dp)
            .verticalScroll(scrollPos)
    ) {
        Text(text = "Hello world, programmed to work but not to feel")

        Card (
            elevation = CardDefaults.cardElevation(5.dp),
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
        ){
            Text(getCurl().toString())
        }

        Card (
            elevation = CardDefaults.cardElevation(5.dp),
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
        ){
            Column {
                Text("Display Large", style = MaterialTheme.typography.displayLarge)
                Text("Display Medium", style = MaterialTheme.typography.displayMedium)
                Text("Display Small", style = MaterialTheme.typography.displaySmall)
                Divider()
                Text("Headline Large", style = MaterialTheme.typography.headlineLarge)
                Text("Headline Medium", style = MaterialTheme.typography.headlineMedium)
                Text("Headline Small", style = MaterialTheme.typography.headlineSmall)
                Divider()
                Text("Body Large", style = MaterialTheme.typography.bodyLarge)
                Text("Body Medium", style = MaterialTheme.typography.bodyMedium)
                Text("Body Small", style = MaterialTheme.typography.bodySmall)
                Divider()
                Text("Label Large", style = MaterialTheme.typography.labelLarge)
                Text("Label Medium", style = MaterialTheme.typography.labelMedium)
                Text("Label Small", style = MaterialTheme.typography.labelSmall)
            }
        }


        Card (
            elevation = CardDefaults.cardElevation(10.dp),
            onClick = {
                navController.navigate("A")
            },
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
        ){
            Column (){
                SubcomposeAsyncImage(
                    model = "https://pbs.twimg.com/media/FaWL8ruacAA87Oz?format=jpg&name=large",
                    loading = {
                        CircularProgressIndicator()
                    },
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(CardDefaults.shape)
                )
                Text("GGubii", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(10.dp, 0.dp))
//                Text("Why are you here", modifier = Modifier.padding(10.dp, 0.dp))
            }
        }


        Card (
            elevation = CardDefaults.cardElevation(10.dp),
            onClick = {
                navController.navigate("B")
            },
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
        ){
            Column {
                SubcomposeAsyncImage(
                    model = "https://pbs.twimg.com/media/FdBjv4FaAAA58jk?format=jpg&name=large",
                    loading = {
                              CircularProgressIndicator()
                    },
                    contentDescription = "naguru's cute ass illustration",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(CardDefaults.shape)
                )

                Text(text = "Naguru", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(10.dp))
            }
        }

//        Button(onClick = {
//            navController.navigate(route = "B")
//        }) {
//            Text(text = "Go to Screen B")
//        }
    }
}

@Composable
fun ScreenA(navController: NavController){
    Column {
        Text(text = "こんにちは。スクリーンAです。")
        AsyncImage(
            model = "https://pbs.twimg.com/media/Fs8-1oXacAA6laD?format=png&name=4096x4096",
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
        )
    }
}

@Composable
fun ScreenB(navController: NavController){
    Text(text = "你好。B。")
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SimpleCard(){
    val paddingModifier  = Modifier.padding(10.dp)
//    Card(
//        onClick = { /* Do something */ },
//        modifier = Modifier
//            .size(width = 180.dp, height = 100.dp)
//            .padding(10.dp),
//        shape = CardDefaults.elevatedShape,
//        elevation = CardDefaults.cardElevation(5.dp),
//        border = BorderStroke(4.dp, MaterialTheme.colorScheme.surfaceTint)
//    ) {
//        Box(Modifier.fillMaxSize()) {
//            Text("Clickable", Modifier.align(Alignment.Center))
//        }
//    }

//    AppEntry()
}