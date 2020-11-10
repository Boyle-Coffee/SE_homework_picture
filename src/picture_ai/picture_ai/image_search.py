# author:Boyle time:2020/11/6
import numpy as np
from .models

def other_main(split_data: tuple) -> tuple:
    return_dict = dict()
    is_success, info, dates, date_values_np_array, hour_max_values_np_array, years, year_values = str_to_num(split_data)
    if not is_success:
        return False, info, None
    if len(date_values_np_array) == 0:
        return False, "date_values is empty or all is null", None
    date_values_max_index = np.argmax(date_values_np_array)
    hour_max_values_max_index = np.argmax(hour_max_values_np_array)
    return_dict["monthAvg"] = str(format(np.mean(date_values_np_array), '.5f'))
    return_dict["dayMaxAvg"] = str(format(date_values_np_array[date_values_max_index], '.3f'))
    return_dict["dayMaxDate"] = dates[date_values_max_index]
    return_dict["hourMaxAvg"] = str(hour_max_values_np_array[hour_max_values_max_index])
    return_dict["hourMaxDate"] = dates[hour_max_values_max_index]

    if len(years) == 0:  
        return_dict["average"] = return_dict["monthAvg"]
        return_dict["ranking"] = "1"
        return_dict["anomaly"] = '0.00000'
        return_dict["yearOnYear"] = "0.00000"
        return_dict["maxValue"] = str(format(float(return_dict["monthAvg"]), '.1f'))
        return_dict["maxYear"] = dates[0][:4]
        return_dict["minValue"] = str(format(float(return_dict["monthAvg"]), '.1f'))
        return_dict["minYear"] = dates[0][:4]
    else:
        years.insert(0, dates[0][:4])
        year_values.insert(0, float(return_dict["monthAvg"]))
        return_dict["average"] = str(np.mean(year_values))
        return_dict["ranking"] = str(np.argsort(year_values)[0])
        return_dict["anomaly"] = str(float(return_dict["monthAvg"]) - float(return_dict["average"]))
        return_dict["yearOnYear"] = str(format(((year_values[0] - year_values[1]) / year_values[1]) * 100, '.7f'))
        return_dict["maxValue"] = str(np.max(year_values))
        return_dict["maxYear"] = years[np.argmax(year_values)]
        return_dict["minValue"] = str(np.min(year_values))
        return_dict["minYear"] = years[np.argmin(year_values)]
    return True, "other_main success run", return_dict
