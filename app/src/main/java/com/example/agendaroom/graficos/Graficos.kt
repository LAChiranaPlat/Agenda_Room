package com.example.agendaroom.graficos

import android.content.pm.ActivityInfo
import android.content.res.TypedArray
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.agendaroom.R
import com.example.agendaroom.data.repo.DBUsuarios
import com.example.agendaroom.databinding.ActivityGraficosBinding
import com.jjoe64.graphview.helper.StaticLabelsFormatter
import com.jjoe64.graphview.series.BarGraphSeries
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.coroutines.launch

class Graficos : AppCompatActivity() {

    lateinit var views:ActivityGraficosBinding

    override fun onPause() {
        super.onPause()
        Log.i("result","Pausado")
   }

    override fun onStop() {
        super.onStop()
        Log.i("result","GRaf Detenido")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("result","Graf Destruido")
    }

    override fun onResume() {
        super.onResume()
        Log.i("result","Resumido")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        views= ActivityGraficosBinding.inflate(layoutInflater)

        setContentView(views.root)

        lifecycleScope.launch {

            var result=DBUsuarios.list()

            Log.i("data",result.size.toString())

            var edades:ArrayList<DataPoint> = ArrayList()
            var alumnos:ArrayList<String> = ArrayList()

            var col=1.0
            result.forEach { user->

                edades.add(
                    DataPoint(col,user.edad.toDouble())
                )
                alumnos.add(user.name)
                col++
            }
            col=1.0

            var datos:BarGraphSeries<DataPoint>
            datos= BarGraphSeries(edades.toTypedArray())

            views.apply {
                graficos.title="Alumnos 2023 LAChirana Plat"
                graficos.titleColor= Color.BLUE
                graficos.titleTextSize=50f

                datos.color=Color.GREEN
                //datos.thickness=10
                //datos.isDrawDataPoints=true

                datos.isAnimated=true
                datos.spacing=10


                graficos.viewport.isYAxisBoundsManual=true
                graficos.viewport.setScrollableY(true)
                graficos.viewport.setMinY(10.0)
                graficos.viewport.setMaxY(80.0)





                var tags=StaticLabelsFormatter(graficos)
                tags.setHorizontalLabels(alumnos.toTypedArray())

                graficos.gridLabelRenderer.labelFormatter=tags

                graficos.addSeries(datos)


        }







        }
    }
}