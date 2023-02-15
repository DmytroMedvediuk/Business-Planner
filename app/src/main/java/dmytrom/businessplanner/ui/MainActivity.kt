package dmytrom.businessplanner.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import dmytrom.businessplanner.data.appComponent
import dmytrom.businessplanner.ui.theme.BusinessPlannerTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels {
        viewModelFactory
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
        viewModel.getAllEvents()

        setContent {

            Scaffold(
                floatingActionButton = { FAB { viewModel.addEvent() } },
                content = {
                    BusinessPlannerTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier
                                .fillMaxSize(),
                            color = Color.Cyan
                        ) {
                            ListOfEvents(viewModel)
                        }
                    }
                }
            )

        }
    }
}

@Composable
fun ListOfEvents(viewModel: MainViewModel) {
    val events = viewModel.events.observeAsState()
    Column {
        if (events.value != null) {
            for (event in events.value!!) {
                Greeting(event.name)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun FAB(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = android.R.drawable.ic_input_add),
            contentDescription = "add"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessPlannerTheme {
        Greeting("Android")
    }
}