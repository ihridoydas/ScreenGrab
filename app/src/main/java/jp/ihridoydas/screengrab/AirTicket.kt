package jp.ihridoydas.screengrab

import android.annotation.SuppressLint
import android.graphics.Matrix
import android.graphics.RectF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.PathParser.createPathFromPathData

@SuppressLint("RestrictedApi")
@Composable
fun AirTicket(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Canvas(
            modifier = Modifier
                .aspectRatio(0.7f)

        ) {
            val fillPath = createPathFromPathData(
                "M 330 481 L 10 481 C 7.348728656768799 480.9970884032082 4.806880950927734 479.9425735473633 2.932147979736328 478.0678405761719 C 1.0574150085449219 476.19310760498047 0.0029115967918211805 473.6512713432312 2.4424906541753444e-15 471 L 0 349.45599365234375 C 4.128947734832764 347.4218542575836 7.616844415664673 344.2882835865021 10.079999923706055 340.3999938964844 C 12.61953616142273 336.3924398422241 13.965030026156455 331.7444386482239 13.958999633789062 327 C 13.965140562038869 322.2555456161499 12.619637966156006 317.6075143814087 10.079999923706055 313.6000061035156 C 7.616764783859253 309.7117838859558 4.128889083862314 306.5782322883606 8.881784197001252e-15 304.54400634765625 L 0 10 C 0.002911596791818738 7.348728656768799 1.0574150085449219 4.806880950927734 2.932147979736328 2.932147979736328 C 4.806880950927734 1.0574150085449219 7.348728656768799 0.002911596791819404 10 6.661338147750939e-16 L 330 0 C 332.6512713432312 0.002911596791818738 335.19310760498047 1.0574150085449219 337.0678405761719 2.932147979736328 C 338.9425735473633 4.806880950927734 339.9970884032082 7.348728656768799 340 10 L 340 304.54400634765625 C 335.80801916122437 306.6103711128235 332.27813363075256 309.8088426589966 329.809814453125 313.7774658203125 C 327.34149527549744 317.7460889816284 326.0332946777344 322.3263964653015 326.0332946777344 327 C 326.0332946777344 331.6736035346985 327.34149527549744 336.2539110183716 329.809814453125 340.2225341796875 C 332.27813363075256 344.1911573410034 335.80801916122437 347.3896288871765 340 349.45599365234375 L 340 471 C 339.9970884032082 473.6512713432312 338.9425735473633 476.19310760498047 337.0678405761719 478.0678405761719 C 335.19310760498047 479.9425735473633 332.6512713432312 480.9970884032082 330 481 Z "
            )
            //fillPath.fillType = Path.FillType.EVEN_ODD
            val rectF = RectF()
            fillPath.computeBounds(rectF, true)
            val matrix = Matrix()
            val scale = minOf(size.width / rectF.width(), size.height / rectF.height())
            matrix.setScale(scale, scale)
            fillPath.transform(matrix)
            val composePathFill = fillPath.asComposePath()

            drawPath(
                path = composePathFill,
                color = Color(
                    red = 0.9921568632125854f,
                    green = 0.9921568632125854f,
                    blue = 0.9921568632125854f,
                    alpha = 1f
                ),
                style = Fill
            )
            drawPath(
                path = composePathFill,
                color = Color.Transparent,
                style = Stroke(width = 3f, miter = 4f, join = StrokeJoin.Round)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyRow(
                modifier = Modifier
                    .padding(start = 0.dp, top = 30.dp, end = 10.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround,     // < -- ココ!
            ) {
                item {
                    Text(
                        text = "Business class",
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp,
                        textDecoration = TextDecoration.None,
                        letterSpacing = 0.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .width(93.dp)
                            //.height(12.dp)
                            .border(
                                width = 1.dp,
                                color = Color(
                                    red = 0.4627451002597809f,
                                    green = 0.7450980544090271f,
                                    blue = 0.14901961386203766f,
                                    alpha = 1f
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(5.dp)
                            .alpha(1f),
                        color = Color(
                            red = 0.4627451002597809f,
                            green = 0.7450980544090271f,
                            blue = 0.14901961386203766f,
                            alpha = 1f
                        ),
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                    )

                }
                item {
                    Spacer(modifier = Modifier.width(50.dp))
                }

                item {
                    ItemForText("JPN")
                }
                item {

                    Canvas(
                        modifier = Modifier
                            .width(16.57807159423828.dp)
                            .height(17.291000366210938.dp)
                        //.fillMaxWidth()
                        //.aspectRatio(1f)

                    ) {
                        val fillPath = createPathFromPathData(
                            "M 4.895073413848877 0.720000147819519 C 4.87181619182229 0.6355044022202492 4.868390923365951 0.5467783808708191 4.885065078735352 0.46074116230010986 C 4.9017392341047525 0.37470394372940063 4.9380622282624245 0.2936811298131943 4.991200923919678 0.2239910364151001 C 5.044339619576931 0.15430094301700592 5.112857647240162 0.09782730415463448 5.191412448883057 0.05897408351302147 C 5.269967250525951 0.020120862871408463 5.356435157358646 -0.00006174772205724821 5.44407320022583 1.419081030462621e-7 C 5.673104673624039 0.0003349245213115637 5.8984998017549515 0.057299040257930756 6.100189208984375 0.16582050919532776 C 6.3018786162137985 0.27434197813272476 6.473603218793869 0.43105287849903107 6.600073337554932 0.6220000982284546 L 10.827073097229004 7.008999824523926 L 15.198073387145996 7.22599983215332 C 15.570207744836807 7.244475498795509 15.920998483896255 7.405299633741379 16.177865982055664 7.675196647644043 C 16.434733480215073 7.945093661546707 16.578016737374128 8.303406953811646 16.57807159423828 8.675999641418457 C 16.578073126604863 9.041836827993393 16.43302011489868 9.392749100923538 16.17470932006836 9.651809692382812 C 15.916398525238037 9.910870283842087 15.565908908843994 10.056940747774206 15.2000732421875 10.057999610900879 L 10.830073356628418 10.057999610900879 L 6.59307336807251 16.663000106811523 C 6.469534806907177 16.855589002370834 6.299566432833672 17.01403408497572 6.09879207611084 17.123769760131836 C 5.898017719388008 17.233505435287952 5.6728793531656265 17.291010943114088 5.44407320022583 17.291000366210938 C 5.356153167784214 17.29102217241234 5.2694031819701195 17.27084670215845 5.190515518188477 17.232030868530273 C 5.111627854406834 17.193215034902096 5.042711474001408 17.136796787381172 4.989082336425781 17.067127227783203 C 4.935453198850155 16.997457668185234 4.898544879630208 16.916399091482162 4.881205081939697 16.8302059173584 C 4.8638652842491865 16.744012743234634 4.866557262837887 16.654987677931786 4.889073371887207 16.56999969482422 L 6.619073390960693 10.057999610900879 L 2.2000732421875 10.057999610900879 L 1.0590732097625732 12.5 C 1.0043352544307709 12.619048103690147 0.9166941121220589 12.719959490001202 0.8064865469932556 12.79083251953125 C 0.6962789818644524 12.861705549061298 0.5681019425392151 12.899583633057773 0.4370732307434082 12.899999618530273 C 0.37900616228580475 12.900009349454194 0.3215194083750248 12.8884467035532 0.26797187328338623 12.865986824035645 C 0.21442433819174767 12.84352694451809 0.165889922529459 12.810620170086622 0.125204399228096 12.769189834594727 C 0.08451887592673302 12.72775949910283 0.05249820277094841 12.67863618209958 0.03101332299411297 12.624690055847168 C 0.009528443217277527 12.570743929594755 -0.0009897754498524591 12.513057261705399 0.0000732381158741191 12.454999923706055 L 0.0000732381158741191 4.508999824523926 C 0.00004677175820688717 4.404454320669174 0.04095185548067093 4.304053507745266 0.11403084546327591 4.229292392730713 C 0.1871098354458809 4.15453127771616 0.286554180085659 4.111352476524189 0.39107322692871094 4.109000205993652 C 0.5078901872038841 4.106354200746864 0.6230162531137466 4.137259803712368 0.7228027582168579 4.198053359985352 C 0.8225892633199692 4.258846916258335 0.9028512090444565 4.346978686749935 0.9540732502937317 4.452000141143799 L 2.2000732421875 7.008999824523926 L 6.619073390960693 7.008999824523926 L 4.895073413848877 0.720000147819519 Z "
                        )
                        //fillPath.fillType = Path.FillType.EVEN_ODD
                        val rectF = RectF()
                        fillPath.computeBounds(rectF, true)
                        val matrix = Matrix()
                        val scale = minOf(size.width / rectF.width(), size.height / rectF.height())
                        matrix.setScale(scale, scale)
                        fillPath.transform(matrix)
                        val composePathFill = fillPath.asComposePath()

                        drawPath(
                            path = composePathFill,
                            color = Color(
                                red = 0.9803921580314636f,
                                green = 0.4274509847164154f,
                                blue = 0.7058823704719543f,
                                alpha = 1f
                            ),
                            style = Fill
                        )
                        drawPath(
                            path = composePathFill,
                            color = Color.Transparent,
                            style = Stroke(width = 3f, miter = 4f, join = StrokeJoin.Round)
                        )
                    }
                }

                item {
                    ItemForText("BAN")
                }


            }

            Text(
                text = "4 Flight Tickets",
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                textDecoration = TextDecoration.None,
                letterSpacing = 0.sp,

                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
                    .alpha(1f),
                color = Color(
                    red = 0.08627451211214066f,
                    green = 0.08627451211214066f,
                    blue = 0.27450981736183167f,
                    alpha = 1f
                ),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            )

            LazyRow(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Absolute.Left,
            ) {
                item {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 5.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        item {
                            ItemForText(
                                "Passengers",
                                color = Color(
                                    red = 0.5529412031173706f,
                                    green = 0.5686274766921997f,
                                    blue = 0.6352941393852234f
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            ItemForText("Hridoy Das", color = Color.Black)
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            ItemForText(
                                "Flight",
                                color = Color(
                                    red = 0.5529412031173706f,
                                    green = 0.5686274766921997f,
                                    blue = 0.6352941393852234f
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            ItemForText("1231673683342", color = Color.Black)
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            ItemForText(
                                "Class",
                                color = Color(
                                    red = 0.5529412031173706f,
                                    green = 0.5686274766921997f,
                                    blue = 0.6352941393852234f
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            ItemForText("Business", color = Color.Black)
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
                item {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, top = 5.dp),
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.Start,
                    ) {
                        item {
                            ItemForText(
                                "Date",
                                color = Color(
                                    red = 0.5529412031173706f,
                                    green = 0.5686274766921997f,
                                    blue = 0.6352941393852234f
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            ItemForText("24-12-2023", color = Color.Black)
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            ItemForText(
                                "Gate",
                                color = Color(
                                    red = 0.5529412031173706f,
                                    green = 0.5686274766921997f,
                                    blue = 0.6352941393852234f
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            ItemForText("66 B", color = Color.Black)
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        item {
                            ItemForText(
                                "Seat",
                                color = Color(
                                    red = 0.5529412031173706f,
                                    green = 0.5686274766921997f,
                                    blue = 0.6352941393852234f
                                )
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            ItemForText("21 B", color = Color.Black)
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
            val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            Canvas(Modifier.fillMaxWidth()
                .padding(top = 20.dp, start = 16.dp, end = 16.dp)
                .height(2.dp)) {

                drawLine(
                    color = Color.DarkGray,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    pathEffect = pathEffect
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                Alignment.Center
            ) {

                Column(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                ) {

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        Alignment.BottomCenter
                    ) {
                        Canvas(
                            modifier = Modifier
                                .width(265.510009765625.dp)
                                .height(59.dp)
                                .fillMaxWidth(),

                            ) {
                            val fillPath = createPathFromPathData(
                                "M 263.6050109863281 59 L 263.6050109863281 0 L 265.510009765625 0 L 265.510009765625 59 L 263.6050109863281 59 Z M 259.1579895019531 59 L 259.1579895019531 0 L 261.0639953613281 0 L 261.0639953613281 59 L 259.1579895019531 59 Z M 252.8070068359375 59 L 252.8070068359375 0 L 257.25299072265625 0 L 257.25299072265625 59 L 252.8070068359375 59 Z M 246.4550018310547 59 L 246.4550018310547 0 L 250.89999389648438 0 L 250.89999389648438 59 L 246.4550018310547 59 Z M 242.64300537109375 59 L 242.64300537109375 0 L 244.5489959716797 0 L 244.5489959716797 59 L 242.64300537109375 59 Z M 236.29100036621094 59 L 236.29100036621094 0 L 238.1999969482422 0 L 238.1999969482422 59 L 236.29100036621094 59 Z M 228.66900634765625 59 L 228.66900634765625 0 L 234.38600158691406 0 L 234.38600158691406 59 L 228.66900634765625 59 Z M 224.85800170898438 59 L 224.85800170898438 0 L 226.76300048828125 0 L 226.76300048828125 59 L 224.85800170898438 59 Z M 218.50599670410156 59 L 218.50599670410156 0 L 220.41099548339844 0 L 220.41099548339844 59 L 218.50599670410156 59 Z M 214.05999755859375 59 L 214.05999755859375 0 L 215.96499633789062 0 L 215.96499633789062 59 L 214.05999755859375 59 Z M 207.70799255371094 59 L 207.70799255371094 0 L 212.1540069580078 0 L 212.1540069580078 59 L 207.70799255371094 59 Z M 201.3560028076172 59 L 201.3560028076172 0 L 205.8000030517578 0 L 205.8000030517578 59 L 201.3560028076172 59 Z M 197.5449981689453 59 L 197.5449981689453 0 L 199.4499969482422 0 L 199.4499969482422 59 L 197.5449981689453 59 Z M 191.1929931640625 59 L 191.1929931640625 0 L 193.10000610351562 0 L 193.10000610351562 59 L 191.1929931640625 59 Z M 183.5709991455078 59 L 183.5709991455078 0 L 189.28700256347656 0 L 189.28700256347656 59 L 183.5709991455078 59 Z M 179.75999450683594 59 L 179.75999450683594 0 L 181.6649932861328 0 L 181.6649932861328 59 L 179.75999450683594 59 Z M 174.04299926757812 59 L 174.04299926757812 0 L 175.947998046875 0 L 175.947998046875 59 L 174.04299926757812 59 Z M 169.60000610351562 59 L 169.60000610351562 0 L 171.5 0 L 171.5 59 L 169.60000610351562 59 Z M 163.2480010986328 59 L 163.2480010986328 0 L 167.69400024414062 0 L 167.69400024414062 59 L 163.2480010986328 59 Z M 156.89599609375 59 L 156.89599609375 0 L 161.34300231933594 0 L 161.34300231933594 59 L 156.89599609375 59 Z M 153.0850067138672 59 L 153.0850067138672 0 L 154.99000549316406 0 L 154.99000549316406 59 L 153.0850067138672 59 Z M 146.73300170898438 59 L 146.73300170898438 0 L 148.63800048828125 0 L 148.63800048828125 59 L 146.73300170898438 59 Z M 139.11099243164062 59 L 139.11099243164062 0 L 144.8280029296875 0 L 144.8280029296875 59 L 139.11099243164062 59 Z M 135.3000030517578 59 L 135.3000030517578 0 L 137.1999969482422 0 L 137.1999969482422 59 L 135.3000030517578 59 Z M 128.947998046875 59 L 128.947998046875 0 L 130.85400390625 0 L 130.85400390625 59 L 128.947998046875 59 Z M 124.5 59 L 124.5 0 L 126.4000015258789 0 L 126.4000015258789 59 L 124.5 59 Z M 118.14800262451172 59 L 118.14800262451172 0 L 122.59500122070312 0 L 122.59500122070312 59 L 118.14800262451172 59 Z M 111.7969970703125 59 L 111.7969970703125 0 L 116.24299621582031 0 L 116.24299621582031 59 L 111.7969970703125 59 Z M 107.98500061035156 59 L 107.98500061035156 0 L 109.88999938964844 0 L 109.88999938964844 59 L 107.98500061035156 59 Z M 101.63300323486328 59 L 101.63300323486328 0 L 103.53900146484375 0 L 103.53900146484375 59 L 101.63300323486328 59 Z M 94.01100158691406 59 L 94.01100158691406 0 L 99.72799682617188 0 L 99.72799682617188 59 L 94.01100158691406 59 Z M 90.19999694824219 59 L 90.19999694824219 0 L 92.0999984741211 0 L 92.0999984741211 59 L 90.19999694824219 59 Z M 83.8489990234375 59 L 83.8489990234375 0 L 85.75399780273438 0 L 85.75399780273438 59 L 83.8489990234375 59 Z M 79.4000015258789 59 L 79.4000015258789 0 L 81.30599975585938 0 L 81.30599975585938 59 L 79.4000015258789 59 Z M 73.04900360107422 59 L 73.04900360107422 0 L 77.49500274658203 0 L 77.49500274658203 59 L 73.04900360107422 59 Z M 66.69999694824219 59 L 66.69999694824219 0 L 71.14600372314453 0 L 71.14600372314453 59 L 66.69999694824219 59 Z M 62.88800048828125 59 L 62.88800048828125 0 L 64.79000091552734 0 L 64.79000091552734 59 L 62.88800048828125 59 Z M 56.5369987487793 59 L 56.5369987487793 0 L 58.44200134277344 0 L 58.44200134277344 59 L 56.5369987487793 59 Z M 48.90999984741211 59 L 48.90999984741211 0 L 54.62699890136719 0 L 54.62699890136719 59 L 48.90999984741211 59 Z M 45.099998474121094 59 L 45.099998474121094 0 L 47 0 L 47 59 L 45.099998474121094 59 Z M 38.74800109863281 59 L 38.74800109863281 0 L 40.65299987792969 0 L 40.65299987792969 59 L 38.74800109863281 59 Z M 34.29999923706055 59 L 34.29999923706055 0 L 36.20500183105469 0 L 36.20500183105469 59 L 34.29999923706055 59 Z M 27.947999954223633 59 L 27.947999954223633 0 L 32.39400100708008 0 L 32.39400100708008 59 L 27.947999954223633 59 Z M 21.600000381469727 59 L 21.600000381469727 0 L 26.047000885009766 0 L 26.047000885009766 59 L 21.600000381469727 59 Z M 17.788999557495117 59 L 17.788999557495117 0 L 19.694000244140625 0 L 19.694000244140625 59 L 17.788999557495117 59 Z M 11.437000274658203 59 L 11.437000274658203 0 L 13.342000007629395 0 L 13.342000007629395 59 L 11.437000274658203 59 Z M 3.812000036239624 59 L 3.812000036239624 0 L 9.527999877929688 0 L 9.527999877929688 59 L 3.812000036239624 59 Z M 0 59 L 0 0 L 1.9049999713897705 0 L 1.9049999713897705 59 L 0 59 Z "
                            )
                            //fillPath.fillType = Path.FillType.EVEN_ODD
                            val rectF = RectF()
                            fillPath.computeBounds(rectF, true)
                            val matrix = Matrix()
                            val scale = minOf(size.width / rectF.width(), size.height / rectF.height())
                            matrix.setScale(scale, scale)
                            fillPath.transform(matrix)
                            val composePathFill = fillPath.asComposePath()

                            drawPath(
                                path = composePathFill,
                                color = Color(
                                    red = 0.25882354378700256f,
                                    green = 0.26274511218070984f,
                                    blue = 0.4156862795352936f,
                                    alpha = 1f
                                ),
                                style = Fill
                            )
                            drawPath(
                                path = composePathFill,
                                color = Color.Transparent,
                                style = Stroke(width = 3f, miter = 4f, join = StrokeJoin.Round)
                            )
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        Alignment.BottomCenter
                    ) {
                        Text(
                            text = "9824 0972 1742 1298",
                            textAlign = TextAlign.Center,
                            fontSize = 11.sp,
                            textDecoration = TextDecoration.None,
                            letterSpacing = 0.sp,

                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = Color(red = 0.3450980484485626f, green = 0.2078431397676468f, blue = 0.7803921699523926f, alpha = 1f),
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                        )

                    }


                }
            }
        }


    }


}


@Composable
fun ItemForText(
    text: String,
    color: Color = Color(
        red = 0.09803921729326248f,
        green = 0.09803921729326248f,
        blue = 0.2823529541492462f,
        alpha = 1f
    ),
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(100))
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            textDecoration = TextDecoration.None,
            letterSpacing = 0.sp,

            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .alpha(1f),
            color = color,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
        )
    }
}

