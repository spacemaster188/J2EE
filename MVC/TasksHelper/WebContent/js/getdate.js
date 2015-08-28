function echo_date( date ){
var days = ["sunday","monday","tuesday","wednesday","thursday","friday","saturday"],
    months = ["January","February","March","April","May","June","July","August","September","October","November","December"];
    echo_date = function(date){
        date = new Date( date );
        return {
            "date" : date,
            "day" : days[ date.getDay() ],
            "month" : months[ date.getMonth() ],
            "day_num" : date.getDate()
        };
    }
    return echo_date(date);  
};