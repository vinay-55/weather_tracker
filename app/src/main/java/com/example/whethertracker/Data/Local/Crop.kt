import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crops")
data class Crop(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val cropType: String,
    val growthDays: Int,

    val soilTypes: String,
    val waterNeed: String,
    val sunlight: String,

    val minTemp: Int,
    val maxTemp: Int,

    val plantSpacingCm: Int
)