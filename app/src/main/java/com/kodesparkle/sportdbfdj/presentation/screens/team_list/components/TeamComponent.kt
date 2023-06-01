package com.kodesparkle.sportdbfdj.presentation.screens.team_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kodesparkle.sportdbfdj.domain.model.TeamItem

@Composable
fun TeamComponent(
    team: TeamItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {

            val (teamImage, teamName) = createRefs()

            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(teamImage) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,

                ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(team.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = team.name
                )

            }
            Text(
                text = team.name,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .constrainAs(teamName) {
                        top.linkTo(teamImage.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .wrapContentHeight(),
                color = Color.Black,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center
            )
        }

    }
}


@Preview(name = "phone", device = "spec:shape=Normal,width=360,height=640, unit=dp,dpi=480")
@Preview(name = "landscape", device = "spec:shape=Normal,width=640,height=360,unit=dp,dpi=480")
@Preview(name = "foldable", device = "spec:shape=Normal,width=673,height=841,unit=dp,dpi=480")
@Composable
fun TeamComponentPreview() {
    TeamComponent(
        team = TeamItem(
            id = "133604",
            name = "Arsenal",
            imageUrl = "https://www.thesportsdb.com/images/media/team/badge/xtwxyt1421431860.png/preview"
        )
    )
}
