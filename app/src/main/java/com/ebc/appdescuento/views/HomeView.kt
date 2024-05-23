package com.ebc.appdescuento.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ebc.appdescuento.R
import com.ebc.appdescuento.components.DosCartas
import com.ebc.appdescuento.viewModels.CalculadoraViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: CalculadoraViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "App descuentos", color = Color.White)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        ContentHomeView(paddingValues = it, viewModel = viewModel)
    }
}

@Composable
fun ContentHomeView(paddingValues: PaddingValues, viewModel: CalculadoraViewModel) {
    Column(
        modifier =
        Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val state = viewModel.state

        DosCartas(
            titulo1 = "Precio Fin", dato1 = state.precioDescuento,
            titulo2 = "Descuento", dato2 = state.totalDescuento
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = state.precio,
            onValueChange = {
                viewModel.onValue(it, "precio")
            },
            label = { Text(text = "Precio")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = state.descuento,
            onValueChange = {
                viewModel.onValue(it, "descuento")
            },
            label = { Text(text = "Descuento %")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(
            onClick = {
                viewModel.calcular()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Text(text = "Generar descuento")
        }

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedButton(
            onClick = {
                viewModel.limpiar()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        ) {
            Text(text = "Limpiar")
        }

        if(state.mostrarAlerta) {
            AlertDialog(
                onDismissRequest = { /*TODO*/ },
                confirmButton = {
                    Button(onClick = { viewModel.cancelarAlerta() }) {
                        Text(text = "Aceptar")
                    }
                },
                title = { Text(text = "Alerta!!!!")},
                text = { Text(text = "Tienes que llenar la información de precio y descuento")}

            )
        }

        Spacer(modifier = Modifier.height(10.dp))
        val imagenDescuento = painterResource(id = R.drawable.descuentos)
        Image(
            painter = imagenDescuento,
            contentDescription = "Imagen descuento"
        )



    }

}