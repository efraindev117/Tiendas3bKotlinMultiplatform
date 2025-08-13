package com.tienda3b.feature.list_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tienda3b.app.core.designsystem.component.CatImageDetail
import com.tienda3b.app.core.designsystem.component.TopAppBarCat
import com.tienda3b.app.core.designsystem.utils.TiendasIcons
import com.tienda3b.app.core.model.CatsUiModelItem
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatsListDetailScreen(
    catId: String,
    viewModel: CatsViewModel = koinViewModel(),
    onBack: () -> Unit,
) {
    val catFlow = remember(catId) { viewModel.observeCatById(catId) }
    val cat by catFlow.collectAsState()

    Scaffold(
        topBar = {
            TopAppBarCat(
                modifier = Modifier,
                onBackClick = { onBack() }
            )
        }
    ) { paddingValues ->
        // Pasa los paddings al contenido para evitar que se solape con la barra superior
        CatDetailContent(cat, modifier = Modifier.padding(paddingValues))
    }
}

@Composable
fun CatDetailContent(cat: CatsUiModelItem?, modifier: Modifier) {
    val catDatabase = cat ?: return
    val scrollState = rememberScrollState()
    Column(modifier = modifier.verticalScroll(scrollState)) {
        CatImageDetail(url = catDatabase.image?.url.orEmpty())
        Column(Modifier.padding(16.dp)) {
            Text(
                text = catDatabase.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = "origin: ${cat.origin}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = cat.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.tertiary
            )
            Spacer(modifier = Modifier.height(20.dp))

            CatStatsSection(cat)
        }
    }
}


@Composable
fun CatAttributeSliderRow(
    label: String,
    value: Int,
    modifier: Modifier = Modifier
) {
    val v = value.coerceIn(0, 5)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(label)
            Text("$v/5")
        }
        Slider(
            modifier = Modifier.fillMaxWidth(),
            value = v.toFloat(),
            onValueChange = {},
            valueRange = 0f..5f,
            steps = 4,
            enabled = false,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                activeTickColor = MaterialTheme.colorScheme.onSecondary,
                inactiveTrackColor = MaterialTheme.colorScheme.surfaceVariant,
                inactiveTickColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}

@Composable
fun CatAttributesSliders(
    attrs: List<Pair<String, Int>>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        attrs.forEach { (label, value) ->
            CatAttributeSliderRow(label, value)
        }
    }
}

@Composable
fun CatStatsSection(cat: CatsUiModelItem) {
    CatAttributesSliders(
        attrs = listOf(
            "Adaptability" to cat.adaptability,
            "Affection level" to cat.affection_level,
            "Child friendly" to cat.child_friendly,
            "Dog friendly" to cat.dog_friendly,
            "Energy level" to cat.energy_level,
            "Grooming" to cat.grooming,
            "Health issues" to cat.health_issues,
            "Intelligence" to cat.intelligence,
            "Shedding level" to cat.shedding_level,
            "Social needs" to cat.social_needs,
            "Stranger friendly" to cat.stranger_friendly,
            "Vocalisation" to cat.vocalisation
        )
    )
}