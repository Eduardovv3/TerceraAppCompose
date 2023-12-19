package com.pmdm.terceraappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.terceraappcompose.R
import com.example.terceraappcompose.ui.theme.TerceraAppComposeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TerceraAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview(modifier: Modifier = Modifier) {
    MainApp()
}


@Composable
fun MainApp(){
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 10.dp, start = 15.dp, end = 15.dp)){
        val (checkbox,text1,text2,text3,boton1,boton2,imagen) = createRefs()
        val barrier = createBottomBarrier(checkbox,text2)
        val textContent: String
        val lista = listOf(
            R.drawable.rey_barbaro,
            R.drawable.reina_arquera,
            R.drawable.gran_centinela,
            R.drawable.luchadora_real
        )
        var imagenselec by remember { mutableStateOf(0) }

        val checkedState = remember { mutableStateOf(false) }
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            modifier = Modifier
                .constrainAs(checkbox){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(text = "Hola buenas",
            modifier = Modifier.constrainAs(text1){
                top.linkTo(checkbox.top)
                start.linkTo(checkbox.end)
                bottom.linkTo((checkbox.bottom))},
            color = Color.Red)
        if(!checkedState.value){
            Text(text = "Parrafo de 3 lineas que se tiene que hacer invisible Parrafo de 3 lineas que se tiene que hacer invisible Parrafo de 3 lineas que se tiene que hacer invisible Parrafo de 3 lineas que se tiene que hacer invisible Parrafo de 3 lineas que se tiene que hacer invisible  ",
                modifier = Modifier
                    .constrainAs(text2){
                        top.linkTo(checkbox.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)},
                color = Color.Red)
            textContent = "Boton no pulsado"
        }else{
            textContent = "Boton pulsado"
        }
        Text(text = textContent,
            modifier = Modifier
                .constrainAs(text3){
                    top.linkTo( barrier)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints},
            color = Color.Red)
        Button(onClick = {imagenselec = (imagenselec - 1 + lista.size) % lista.size},
            modifier = Modifier.constrainAs(boton1){
                start.linkTo(parent.start)
                bottom.linkTo(parent.bottom)
            }) {
            Text(text = "Anterior")
        }

        Image(
            painter = painterResource(id = lista[imagenselec]),
            contentDescription = "imagen",
            modifier = Modifier
                .size(100.dp)
                .constrainAs(imagen) {
                start.linkTo(boton1.end)
                end.linkTo(boton2.start)
                bottom.linkTo(parent.bottom)
            }
        )

        Button(onClick = {imagenselec = (imagenselec + 1) % lista.size},
            modifier = Modifier.constrainAs(boton2){
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }) {
            Text(text = "Siguiente")
        }
    }

}