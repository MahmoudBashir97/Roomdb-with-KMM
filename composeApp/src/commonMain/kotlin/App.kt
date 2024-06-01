import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.room.RoomDatabase
import cafe.adriel.voyager.navigator.Navigator
import database.UserDatabase
import di.initKoin
import org.jetbrains.compose.ui.tooling.preview.Preview
import screen.PostsScreen

@Composable
@Preview
fun App(databaseBuilder: RoomDatabase.Builder<UserDatabase>) {
    initKoin(databaseBuilder.build())

    MaterialTheme {
        Navigator(PostsScreen())
    }
}